package com.smartHomeAutomationSystem.controller;

import com.smartHomeAutomationSystem.dto.request.DeviceRequest;
import com.smartHomeAutomationSystem.dto.response.ApiResponse;
import com.smartHomeAutomationSystem.dto.response.DeviceResponse;
import com.smartHomeAutomationSystem.entity.Device;
import com.smartHomeAutomationSystem.entity.DeviceLog;
import com.smartHomeAutomationSystem.entity.Room;
import com.smartHomeAutomationSystem.service.DeviceLogService;
import com.smartHomeAutomationSystem.service.DeviceService;
import com.smartHomeAutomationSystem.service.RoomService;
import com.smartHomeAutomationSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.smartHomeAutomationSystem.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.smartHomeAutomationSystem.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/devices")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final RoomService roomService;
    private final UserService userService;

    private final DeviceLogService deviceLogService;

    @GetMapping
    public ResponseEntity<List<DeviceResponse>> getAllDevices(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<Device> devices = deviceService.getDevicesByUser(userDetails.getId());
        List<DeviceResponse> responses = devices.stream().map(device -> {
            DeviceResponse r = new DeviceResponse();
            r.setId(device.getId());
            r.setName(device.getName());
            r.setType(device.getType());
            r.setStatus(device.getStatus());
            r.setLocation(device.getLocation());
            r.setLastUpdated(device.getLastUpdated());
            r.setRoomId(device.getRoom() != null ? device.getRoom().getId() : null);
            r.setOwnerId(device.getOwner().getId());
            return r;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponse> getDeviceById(@PathVariable Long id) {
        Device device = deviceService.findById(id);
        DeviceResponse response = new DeviceResponse();
        response.setId(device.getId());
        response.setName(device.getName());
        response.setType(device.getType());
        response.setStatus(device.getStatus());
        response.setLocation(device.getLocation());
        response.setLastUpdated(device.getLastUpdated());
        response.setRoomId(device.getRoom() != null ? device.getRoom().getId() : null);
        response.setOwnerId(device.getOwner().getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addDevice(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody DeviceRequest request) {
        User owner = userService.findById(userDetails.getId());
        Device device = Device.builder()
                .name(request.getName())
                .type(request.getType())
                .status(request.getStatus())
                .location(request.getLocation())
                .owner(owner)
                .build();

        if (request.getRoomId() != null) {
            Room room = roomService.findById(request.getRoomId());
            device.setRoom(room);
        }

        deviceService.save(device);
        return ResponseEntity.ok(new ApiResponse(true, "Device added", null));
    }

    @PostMapping("/{id}/command")
    public ResponseEntity<ApiResponse> sendCommandToDevice(
            @PathVariable Long id,
            @RequestBody Map<String, Object> command) {
        String cmd = (String) command.get("command");
        Object payload = command.get("payload");
        deviceService.sendCommandToDevice(id, cmd, payload);
        return ResponseEntity.ok(new ApiResponse(true, "Command sent to device", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteDevice(@PathVariable Long id) {
        deviceService.deleteById(id);
        return ResponseEntity.ok(new ApiResponse(true, "Device deleted", null));
    }

    @GetMapping("/{id}/logs")
    public List<DeviceLog> getDeviceLogs(@PathVariable Long id) {
        return deviceLogService.getLogsByDeviceId(id);
    }

    @GetMapping("/{id}/logs/daily")
    public List<DeviceLog> getDeviceLogsToday(@PathVariable Long id) {
        LocalDateTime start = LocalDate.now().atStartOfDay();
        LocalDateTime end = start.plusDays(1);
        return deviceLogService.getLogsByDeviceAndTimeRange(id, start, end);
    }
}