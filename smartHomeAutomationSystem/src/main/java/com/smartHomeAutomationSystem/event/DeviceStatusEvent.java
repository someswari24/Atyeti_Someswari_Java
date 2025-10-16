package com.smartHomeAutomationSystem.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatusEvent {
    private String deviceId;
    private String status;
    private long timestamp;
}