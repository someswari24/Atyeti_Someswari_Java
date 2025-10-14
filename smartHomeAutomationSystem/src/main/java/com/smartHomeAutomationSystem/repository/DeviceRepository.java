package com.smartHomeAutomationSystem.repository;

import com.smartHomeAutomationSystem.entity.Device;
import com.smartHomeAutomationSystem.enums.DeviceStatus;
import com.smartHomeAutomationSystem.enums.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceRepository extends JpaRepository<Device,Long> {
    List<Device> findByRoomId(Long roomId);
    List<Device> findByType(DeviceType type);
    List<Device> findByStatus(DeviceStatus status);
    List<Device>findByOwnerId(Long ownerId);
}
