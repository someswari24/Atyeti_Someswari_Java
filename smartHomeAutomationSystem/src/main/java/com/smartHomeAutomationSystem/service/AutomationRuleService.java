package com.smartHomeAutomationSystem.service;

import com.smartHomeAutomationSystem.entity.AutomationRule;
import com.smartHomeAutomationSystem.entity.Device;
import com.smartHomeAutomationSystem.enums.AutomationAction;
import com.smartHomeAutomationSystem.event.KafkaDeviceEventProducer;
import com.smartHomeAutomationSystem.repository.AutomationRuleRepository;
import com.smartHomeAutomationSystem.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutomationRuleService {

    private final AutomationRuleRepository ruleRepository;
    private final DeviceRepository deviceRepository;
    private final KafkaDeviceEventProducer kafkaProducer;

    public void evaluateTemperatureRules(String deviceId, double temperature) {
        try {
            Long deviceIdLong = Long.parseLong(deviceId);
            List<AutomationRule> rules = ruleRepository.findByDeviceId(deviceIdLong);

            for (AutomationRule rule : rules) {
                if (!rule.isEnabled()) {
                    continue;
                }

                if (evaluateCondition(rule.getCondition(), temperature)) {
                    log.info("Automation rule '{}' triggered for device {}", rule.getRuleName(), deviceId);
                    executeAction(rule.getAction(), deviceId, rule);
                }
            }
        } catch (NumberFormatException e) {
            log.error("Invalid device ID in temperature event: {}", deviceId, e);
        } catch (Exception e) {
            log.error("Error evaluating automation rules for device {}: {}", deviceId, e.getMessage(), e);
        }
    }

    private boolean evaluateCondition(String condition, double temperature) {
        if (condition == null || condition.trim().isEmpty()) {
            return false;
        }

        String cond = condition.trim();

        if (cond.startsWith("temperature >")) {
            try {
                String thresholdStr = cond.substring("temperature >".length()).trim();
                double threshold = Double.parseDouble(thresholdStr);
                return temperature > threshold;
            } catch (NumberFormatException e) {
                log.warn("Invalid threshold in condition: '{}'", condition);
                return false;
            }
        }

        log.debug("Unsupported condition format: '{}'", condition);
        return false;
    }

    private void executeAction(AutomationAction action, String deviceId, AutomationRule rule) {
        switch (action) {
            case TURN_ON -> {
                kafkaProducer.sendCommandToDevice(deviceId, "TURN_ON", null);
                log.debug("Sent TURN_ON command to device {}", deviceId);
            }
            case TURN_OFF -> {
                kafkaProducer.sendCommandToDevice(deviceId, "TURN_OFF", null);
                log.debug("Sent TURN_OFF command to device {}", deviceId);
            }
            case ADJUST_TEMPERATURE -> {
                Map<String, Object> payload = Map.of("target", 24.0);
                kafkaProducer.sendCommandToDevice(deviceId, "SET_TEMP", payload);
                log.debug("Sent SET_TEMP command to device {} with payload: {}", deviceId, payload);
            }
            case SEND_ALERT -> {
                String alertMsg = "Automation rule triggered: " + rule.getRuleName();
                kafkaProducer.sendSecurityAlert(deviceId, "RULE_TRIGGERED", alertMsg);
                log.warn("Sent security alert for rule: {}", rule.getRuleName());
            }
            case LOCK, UNLOCK, RECORD_VIDEO, CUSTOM_ACTION -> {
                log.warn("Action '{}' is not yet implemented for automation rules", action);
            }
            default -> log.warn("Unknown automation action: {}", action);
        }
    }

    public AutomationRule save(AutomationRule rule) {
        return ruleRepository.save(rule);
    }

    public List<AutomationRule> getRulesByUser(Long userId) {
        List<Device> devices = deviceRepository.findByOwnerId(userId);
        List<Long> deviceIds = devices.stream().map(Device::getId).toList();
        return ruleRepository.findByDeviceIdIn(deviceIds);
    }
    public List<AutomationRule> getRulesByDevice(Long deviceId) {
        return ruleRepository.findByDeviceId(deviceId);
    }
}