package com.smartHomeAutomationSystem.controller;

import com.smartHomeAutomationSystem.dto.request.RoomRequest;
import com.smartHomeAutomationSystem.dto.response.ApiResponse;
import com.smartHomeAutomationSystem.dto.response.RoomResponse;
import com.smartHomeAutomationSystem.entity.Room;
import com.smartHomeAutomationSystem.entity.User;
import com.smartHomeAutomationSystem.security.CustomUserDetails;
import com.smartHomeAutomationSystem.service.RoomService;
import com.smartHomeAutomationSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;
    private final UserService userService;

    @GetMapping
    public List<RoomResponse> getRooms(@AuthenticationPrincipal CustomUserDetails user) {
        return roomService.getRoomsByUser(user.getId()).stream()
                .map(this::toResponse)
                .toList();
    }

    @PostMapping
    public ApiResponse createRoom(@RequestBody RoomRequest request,
                                  @AuthenticationPrincipal CustomUserDetails user) {
        Room room = new Room();
        room.setName(request.getName());
        room.setType(request.getType());

        User owner = userService.findById(user.getId());
        room.setOwner(owner);

        roomService.save(room);
        return new ApiResponse(true, "Room created", null);
    }

    private RoomResponse toResponse(Room room) {
        RoomResponse response = new RoomResponse();
        response.setId(room.getId());
        response.setName(room.getName());
        response.setType(room.getType());
        response.setOwnerId(room.getOwner() != null ? room.getOwner().getId() : null);
        return response;
    }
}
