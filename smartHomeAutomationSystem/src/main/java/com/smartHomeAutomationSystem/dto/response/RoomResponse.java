package com.smartHomeAutomationSystem.dto.response;

import com.smartHomeAutomationSystem.enums.RoomType;
import lombok.Data;

@Data
public class RoomResponse {
    private Long id;
    private String name;
    private RoomType type;
    private Long ownerId;
}