package com.smartHomeAutomationSystem.dto.response;

import com.smartHomeAutomationSystem.enums.AutomationAction;
import lombok.Data;

import java.time.LocalTime;

@Data
public class AutomationRuleResponse {
    private Long id;
    private String ruleName;
    private Long deviceId;
    private String condition;
    private AutomationAction action;
    private LocalTime triggerTime;
    private boolean enabled;
}