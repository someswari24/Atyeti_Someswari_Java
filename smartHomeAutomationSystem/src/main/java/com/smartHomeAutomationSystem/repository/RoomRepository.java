package com.smartHomeAutomationSystem.repository;

import com.smartHomeAutomationSystem.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {
    List<Room> findByOwner_Id(Long userId);
    boolean existsByNameAndOwner_Id(String name, Long userId);
}
