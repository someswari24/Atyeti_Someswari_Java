package com.smartHomeAutomationSystem.repository;

import com.smartHomeAutomationSystem.entity.Notification;
import com.smartHomeAutomationSystem.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByType(NotificationType type);

}
