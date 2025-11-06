package com.smartHomeAutomationSystem.event;

import com.smartHomeAutomationSystem.config.KafkaConfig;
import com.smartHomeAutomationSystem.entity.Device;
import com.smartHomeAutomationSystem.enums.DeviceStatus;
import com.smartHomeAutomationSystem.service.DeviceLogService;
import com.smartHomeAutomationSystem.service.DeviceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaDeviceCommandConsumer {

    private final DeviceService deviceService;
    private final DeviceLogService deviceLogService;

    @KafkaListener(topics = KafkaConfig.DEVICE_COMMANDS_TOPIC, groupId = "sihas-group")
    public void handleDeviceCommand(DeviceCommandEvent event) {
        log.info("Processing command for device {}: {}", event.getDeviceId(), event.getCommand());

        try {
            Long deviceId = Long.parseLong(event.getDeviceId());
            Device device = deviceService.findById(deviceId);

            String cmd = event.getCommand().toUpperCase();
            if ("TURN_ON".equals(cmd)) {
                device.setStatus(DeviceStatus.ON);
            } else if ("TURN_OFF".equals(cmd)) {
                device.setStatus(DeviceStatus.OFF);
            } else if ("SET_TEMP".equals(cmd)) {
                log.debug("Temperature command ignored in consumer (simulation only)");
            } else {
                log.warn("Unsupported command: {}", cmd);
                return;
            }

            deviceService.save(device);
            deviceLogService.logDeviceEvent(deviceId, "Executed command: " + cmd);

        } catch (NumberFormatException e) {
            log.error("Invalid device ID: {}", event.getDeviceId(), e);
        } catch (Exception e) {
            log.error("Error handling command: {}", event, e);
        }
    }
}