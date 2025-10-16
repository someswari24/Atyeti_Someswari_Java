package com.smartHomeAutomationSystem.dto.request;

import com.smartHomeAutomationSystem.enums.DeviceType;
import com.smartHomeAutomationSystem.enums.DeviceStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeviceRequest {
    @NotBlank
    private String name;

    @NotNull
    private DeviceType type;

    private DeviceStatus status = DeviceStatus.OFF;

    @NotBlank
    private String location;

    private Long roomId;
}
