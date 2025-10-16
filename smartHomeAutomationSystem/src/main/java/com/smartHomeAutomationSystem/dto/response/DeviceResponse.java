package com.smartHomeAutomationSystem.dto.response;

import com.smartHomeAutomationSystem.enums.DeviceType;
import com.smartHomeAutomationSystem.enums.DeviceStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeviceResponse {
    private Long id;
    private String name;
    private DeviceType type;
    private DeviceStatus status;
    private String location;
    private LocalDateTime lastUpdated;
    private Long roomId;
    private Long ownerId;
}