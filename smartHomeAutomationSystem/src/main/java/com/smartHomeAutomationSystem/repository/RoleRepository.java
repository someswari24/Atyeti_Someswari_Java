package com.smartHomeAutomationSystem.repository;

import com.smartHomeAutomationSystem.entity.Role;
import com.smartHomeAutomationSystem.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role>findByName(String name);
}
