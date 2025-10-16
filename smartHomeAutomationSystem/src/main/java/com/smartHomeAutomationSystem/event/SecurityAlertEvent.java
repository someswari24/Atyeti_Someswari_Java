package com.smartHomeAutomationSystem.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SecurityAlertEvent {
    private String deviceId;
    private String alertType;
    private String message;
    private long timestamp;
}