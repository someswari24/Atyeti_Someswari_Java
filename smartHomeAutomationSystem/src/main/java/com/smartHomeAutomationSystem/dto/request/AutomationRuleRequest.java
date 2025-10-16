package com.smartHomeAutomationSystem.dto.request;

import com.smartHomeAutomationSystem.enums.AutomationAction;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class AutomationRuleRequest {
    @NotBlank
    private String ruleName;

    @NotNull
    private Long deviceId;

    @NotBlank
    private String condition;

    @NotNull
    private AutomationAction action;

    private LocalTime triggerTime;

    private boolean enabled = true;
}