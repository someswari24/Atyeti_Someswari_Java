package com.smartHomeAutomationSystem.dto.request;

import com.smartHomeAutomationSystem.enums.RoomType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RoomRequest {
    @NotBlank
    private String name;

    @NotNull
    private RoomType type;
}
