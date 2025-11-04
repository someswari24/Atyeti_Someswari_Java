package com.smartHomeAutomationSystem.controller;

import com.smartHomeAutomationSystem.dto.request.LoginRequest;
import com.smartHomeAutomationSystem.dto.request.RegisterRequest;
import com.smartHomeAutomationSystem.dto.response.ApiResponse;
import com.smartHomeAutomationSystem.dto.response.JwtResponse;
import com.smartHomeAutomationSystem.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", null));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest request) {
        JwtResponse jwtResponse = authService.login(request);
        return ResponseEntity.ok(jwtResponse);
    }
}