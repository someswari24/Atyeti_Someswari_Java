package com.smartHomeAutomationSystem.service;

import com.smartHomeAutomationSystem.entity.Device;
import com.smartHomeAutomationSystem.entity.DeviceLog;
import com.smartHomeAutomationSystem.exception.DeviceNotFoundException;
import com.smartHomeAutomationSystem.repository.DeviceLogRepository;
import com.smartHomeAutomationSystem.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceLogService {

    private final DeviceLogRepository deviceLogRepository;
    private final DeviceRepository deviceRepository;

    @Transactional
    public DeviceLog logDeviceEvent(Long deviceId, String message) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found: " + deviceId));
        DeviceLog log = DeviceLog.builder()
                .device(device)
                .message(message)
                .timestamp(LocalDateTime.now())
                .build();
        return deviceLogRepository.save(log);
    }

    public List<DeviceLog> getLogsByDeviceId(Long deviceId) {
        if (!deviceRepository.existsById(deviceId)) {
            throw new DeviceNotFoundException("Device not found: " + deviceId);
        }
        return deviceLogRepository.findByDeviceId(deviceId);
    }

    public List<DeviceLog> getLogsBetween(LocalDateTime start, LocalDateTime end) {
        return deviceLogRepository.findByTimestampBetween(start, end);
    }

    public List<DeviceLog> getLogsByDeviceAndTimeRange(Long deviceId, LocalDateTime start, LocalDateTime end) {
        if (!deviceRepository.existsById(deviceId)) {
            throw new DeviceNotFoundException("Device not found: " + deviceId);
        }
        return deviceLogRepository.findByDeviceIdAndTimestampBetween(deviceId, start, end);
    }

    @Transactional
    public void deleteLogsByDeviceId(Long deviceId) {
        deviceLogRepository.deleteByDeviceId(deviceId);
    }
}