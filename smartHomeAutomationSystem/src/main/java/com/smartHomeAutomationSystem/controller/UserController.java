package com.smartHomeAutomationSystem.controller;

import com.smartHomeAutomationSystem.dto.response.ApiResponse;
import com.smartHomeAutomationSystem.dto.response.UserResponse;
import com.smartHomeAutomationSystem.entity.User;
import com.smartHomeAutomationSystem.enums.RoleType;
import com.smartHomeAutomationSystem.security.CustomUserDetails;
import com.smartHomeAutomationSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        User user = userService.findById(userDetails.getId());
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(
            @PathVariable Long id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        userService.updateUser(id, name, email);
        return ResponseEntity.ok(new ApiResponse(true, "User updated", null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse(true, "User deleted", null));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{id}/assign-role")
    public ResponseEntity<ApiResponse> assignRole(
            @PathVariable Long id,
            @RequestParam RoleType role) {
        userService.assignRoleToUser(id, role);
        return ResponseEntity.ok(new ApiResponse(true, "Role assigned", null));
    }
}
