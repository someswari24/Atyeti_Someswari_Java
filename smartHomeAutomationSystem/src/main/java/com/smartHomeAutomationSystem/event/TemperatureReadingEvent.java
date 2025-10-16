package com.smartHomeAutomationSystem.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureReadingEvent {
    private String deviceId;
    private double temperature;
    private long timestamp;
}