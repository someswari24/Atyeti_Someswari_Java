package com.smartHomeAutomationSystem.controller;

import com.smartHomeAutomationSystem.dto.request.RoomRequest;
import com.smartHomeAutomationSystem.dto.response.ApiResponse;
import com.smartHomeAutomationSystem.dto.response.RoomResponse;
import com.smartHomeAutomationSystem.entity.Room;
import com.smartHomeAutomationSystem.entity.User;
import com.smartHomeAutomationSystem.security.CustomUserDetails;
import com.smartHomeAutomationSystem.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
public class RoomController {
    private final RoomService roomService;

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
        room.setOwner(new User(user.getId(), null, null, null, null));
        roomService.save(room);
        return new ApiResponse(true, "Room created", null);
    }
}
