package com.smartHomeAutomationSystem.service;

import com.smartHomeAutomationSystem.entity.Role;
import com.smartHomeAutomationSystem.entity.User;
import com.smartHomeAutomationSystem.enums.RoleType;
import com.smartHomeAutomationSystem.exception.ResourceNotFoundException;
import com.smartHomeAutomationSystem.repository.RoleRepository;
import com.smartHomeAutomationSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Transactional
    public User updateUser(Long userId, String name, String email) {
        User user = findById(userId);
        if (name != null) user.setName(name);
        if (email != null && !email.equals(user.getEmail())) {
            if (userRepository.existsByEmail(email)) {
                throw new RuntimeException("Email already taken");
            }
            user.setEmail(email);
        }
        return userRepository.save(user);
    }

    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    @Transactional
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found");
        }
        userRepository.deleteById(userId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void assignRoleToUser(Long userId, RoleType roleType) {
        User user = findById(userId);
        Role role = roleRepository.findByName(roleType)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleType));
        user.getRoles().add(role);
        userRepository.save(user);
    }
}