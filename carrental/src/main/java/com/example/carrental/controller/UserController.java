package com.example.carrental.controller;

import com.example.carrental.model.User;
import com.example.carrental.model.enums.Role;
import com.example.carrental.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> add(@RequestBody User user) {
        User savedUser = userService.registerUser(user);
        log.info("User registered: "+savedUser.getUsername());
        return ResponseEntity.created(URI.create("/users/" + savedUser.getId())).body(savedUser);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> findByUsername(@PathVariable String username) {
        return userService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<User> findByEmail(@PathVariable String email) {
        return userService.findByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/role/{role}")
    public List<User> findUsersByRole(@PathVariable Role role) {
        return userService.findUsersByRole(role);
    }


    @GetMapping
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }
}
