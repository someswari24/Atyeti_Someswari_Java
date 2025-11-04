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
public class KafkaDeviceEventConsumer {

    private final DeviceService deviceService;
    private final DeviceLogService deviceLogService;

    @KafkaListener(
            topics = KafkaConfig.DEVICE_STATUS_TOPIC,
            groupId = "sihas-group")
    public void handleDeviceCommand(DeviceCommandEvent event) {
        log.info("Received command for device {}: {} with payload: {}",
                event.getDeviceId(), event.getCommand(), event.getPayload());

        try {
            Long deviceId = Long.parseLong(event.getDeviceId());
            Device device = deviceService.findById(deviceId);

            String command = event.getCommand().toUpperCase();
            DeviceStatus newStatus = null;

            switch (command) {
                case "TURN_ON":
                    newStatus = DeviceStatus.ON;
                    break;
                case "TURN_OFF":
                    newStatus = DeviceStatus.OFF;
                    break;
                case "SET_TEMP":
                    log.debug("SET_TEMP command received for device {}. Payload: {}", deviceId, event.getPayload());
                    deviceLogService.logDeviceEvent(deviceId, "Temperature command processed: " + event.getPayload());
                    return;
                default:
                    log.warn("Unsupported command: {}", command);
                    deviceLogService.logDeviceEvent(deviceId, "Unsupported command: " + command);
                    return;
            }

            device.setStatus(newStatus);
            deviceService.save(device);

            deviceLogService.logDeviceEvent(deviceId, "Command executed: " + command + " → Status set to " + newStatus);

        } catch (NumberFormatException e) {
            log.error("Invalid device ID in command: {}", event.getDeviceId(), e);
        } catch (Exception e) {
            log.error("Error handling device command: {}", event, e);
        }
    }
}