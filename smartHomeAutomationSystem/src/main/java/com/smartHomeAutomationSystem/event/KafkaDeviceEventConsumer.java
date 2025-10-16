package com.smartHomeAutomationSystem.event;

import com.smartHomeAutomationSystem.config.KafkaConfig;
import com.smartHomeAutomationSystem.service.AutomationRuleService;
import com.smartHomeAutomationSystem.service.DeviceLogService;
import com.smartHomeAutomationSystem.service.DeviceService;
import com.smartHomeAutomationSystem.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaDeviceEventConsumer {

    private final DeviceService deviceService;
    private final AutomationRuleService automationRuleService;
    private final NotificationService notificationService;
    private final DeviceLogService deviceLogService;

    @KafkaListener(topics = KafkaConfig.DEVICE_STATUS_TOPIC, groupId = "sihas-group")
    public void handleDeviceStatus(DeviceStatusEvent event) {
        log.info("Consumed device status: {}", event);
        try {
            deviceService.updateDeviceStatusFromEvent(event);
            deviceLogService.logDeviceEvent(
                    Long.parseLong(event.getDeviceId()),
                    "Status updated to: " + event.getStatus()
            );
        } catch (Exception e) {
            log.error("Error processing device status event", e);
        }
    }

    @KafkaListener(topics = KafkaConfig.TEMPERATURE_SENSOR_TOPIC, groupId = "sihas-group")
    public void handleTemperatureReading(TemperatureReadingEvent event) {
        log.info("Consumed temperature reading: {}", event);
        try {
            automationRuleService.evaluateTemperatureRules(event.getDeviceId(), event.getTemperature());
            deviceLogService.logDeviceEvent(
                    Long.parseLong(event.getDeviceId()),
                    "Temperature: " + event.getTemperature() + "°C"
            );
        } catch (Exception e) {
            log.error("Error processing temperature event", e);
        }
    }

    @KafkaListener(topics = KafkaConfig.SECURITY_ALERTS_TOPIC, groupId = "sihas-group")
    public void handleSecurityAlert(SecurityAlertEvent event) {
        log.warn("Consumed security alert: {}", event);
        try {
            notificationService.createSecurityAlert(event.getDeviceId(), event.getMessage());
            deviceLogService.logDeviceEvent(
                    Long.parseLong(event.getDeviceId()),
                    "Security alert: " + event.getMessage()
            );
        } catch (Exception e) {
            log.error("Error processing security alert", e);
        }
    }
    
}