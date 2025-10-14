package com.smartHomeAutomationSystem.repository;

import com.smartHomeAutomationSystem.entity.DeviceLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface DeviceLogRepository extends JpaRepository<DeviceLog,Long> {
    List<DeviceLog> findByDeviceId(Long deviceId);
    List<DeviceLog> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
    List<DeviceLog>findByDeviceIdAndTimestampBetween(Long deviceId,LocalDateTime start, LocalDateTime end);
    List<DeviceLog>deleteByDeviceId(Long deviceId);
}
