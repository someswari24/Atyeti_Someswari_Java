package com.smartHomeAutomationSystem.event;

import com.smartHomeAutomationSystem.config.KafkaConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaDeviceEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void sendDeviceStatus(String deviceId, String status) {
        DeviceStatusEvent event = new DeviceStatusEvent(deviceId, status, System.currentTimeMillis());
        kafkaTemplate.send(KafkaConfig.DEVICE_STATUS_TOPIC, deviceId, event);
        log.debug("Sent device status event: {}", event);
    }

    public void sendTemperatureReading(String deviceId, double temperature) {
        TemperatureReadingEvent event = new TemperatureReadingEvent(deviceId, temperature, System.currentTimeMillis());
        kafkaTemplate.send(KafkaConfig.TEMPERATURE_SENSOR_TOPIC, deviceId, event);
        log.debug("Sent temperature event: {}", event);
    }

    public void sendSecurityAlert(String deviceId, String alertType, String message) {
        SecurityAlertEvent event = new SecurityAlertEvent(deviceId, alertType, message, System.currentTimeMillis());
        kafkaTemplate.send(KafkaConfig.SECURITY_ALERTS_TOPIC, deviceId, event);
        log.warn("Sent security alert: {}", event);
    }

    public void sendCommandToDevice(String deviceId, String command, java.util.Map<String, Object> payload) {
        DeviceCommandEvent event = new DeviceCommandEvent(deviceId, command, payload, System.currentTimeMillis());
        kafkaTemplate.send(KafkaConfig.DEVICE_COMMANDS_TOPIC, deviceId, event);
        log.info("Sent command to device {}: {}", deviceId, command);
    }
}