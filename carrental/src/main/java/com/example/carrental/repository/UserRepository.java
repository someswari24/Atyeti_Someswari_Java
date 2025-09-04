package com.example.carrental.repository;

import com.example.carrental.model.User;
import com.example.carrental.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<User>findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findUsersByRole(Role role);
}
