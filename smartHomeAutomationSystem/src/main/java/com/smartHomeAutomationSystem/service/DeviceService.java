package com.smartHomeAutomationSystem.service;

import com.smartHomeAutomationSystem.entity.Device;
import com.smartHomeAutomationSystem.entity.DeviceLog;
import com.smartHomeAutomationSystem.enums.DeviceStatus;
import com.smartHomeAutomationSystem.event.DeviceStatusEvent;
import com.smartHomeAutomationSystem.event.KafkaDeviceEventProducer;
import com.smartHomeAutomationSystem.exception.DeviceNotFoundException;
import com.smartHomeAutomationSystem.repository.DeviceLogRepository;
import com.smartHomeAutomationSystem.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceLogRepository deviceLogRepository;
    private final KafkaDeviceEventProducer kafkaProducer;

    @Cacheable(value = "devices", key = "#id")
    public Device findById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found: " + id));
    }

    public List<Device> getDevicesByUser(Long userId) {
        return deviceRepository.findByOwnerId(userId);
    }

    @CacheEvict(value = "devices", key = "#device.id")
    public Device save(Device device) {
        device.setLastUpdated(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    @CacheEvict(value = "devices", key = "#id")
    public void deleteById(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new DeviceNotFoundException("Device not found: " + id);
        }
        deviceRepository.deleteById(id);
        deviceLogRepository.deleteByDeviceId(id);
    }

    @Transactional
    public void updateDeviceStatusFromEvent(DeviceStatusEvent event) {
        Long deviceId = Long.parseLong(event.getDeviceId());
        Device device = findById(deviceId);

        DeviceStatus status = DeviceStatus.valueOf(event.getStatus());
        device.setStatus(status);
        save(device);
    }

    public void sendCommandToDevice(Long deviceId, String command, Object data) {
        Device device = findById(deviceId);
        kafkaProducer.sendCommandToDevice(deviceId.toString(), command, (Map<String, Object>) data);
        deviceLogRepository.save(DeviceLog.builder()
                .device(device)
                .message("Command sent: " + command + " | Data: " + data)
                .timestamp(LocalDateTime.now())
                .build());
    }
}