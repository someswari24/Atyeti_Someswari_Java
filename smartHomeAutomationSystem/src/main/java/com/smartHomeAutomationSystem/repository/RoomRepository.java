package com.smartHomeAutomationSystem.repository;

import com.smartHomeAutomationSystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByUserId(Long userId);
    boolean existsByNameAndUserId(String name, Long userId);
}
