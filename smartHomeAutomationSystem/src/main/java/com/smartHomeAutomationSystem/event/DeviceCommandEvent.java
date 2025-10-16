package com.smartHomeAutomationSystem.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceCommandEvent {
    private String deviceId;
    private String command;
    private Map<String, Object> payload;
    private long timestamp;
}