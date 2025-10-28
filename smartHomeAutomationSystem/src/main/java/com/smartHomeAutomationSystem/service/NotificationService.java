package com.smartHomeAutomationSystem.service;

import com.smartHomeAutomationSystem.entity.Notification;
import com.smartHomeAutomationSystem.entity.User;
import com.smartHomeAutomationSystem.enums.NotificationType;
import com.smartHomeAutomationSystem.repository.NotificationRepository;
import com.smartHomeAutomationSystem.websocket.WebSocketNotificationHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final WebSocketNotificationHandler webSocketHandler;

    public List<Notification> getNotificationsByUser(Long userId) {
        return notificationRepository.findByUserId(userId);
    }

    public Notification createNotification(Long userId, String title, String message, NotificationType type) {
        Notification notification = Notification.builder()
                .user(User.builder().id(userId).build())
                .title(title)
                .message(message)
                .type(type)
                .timestamp(LocalDateTime.now())
                .read(false)
                .build();

        Notification saved = notificationRepository.save(notification);
        webSocketHandler.sendToUser(userId.toString(), saved);
        return saved;
    }

    public void createSecurityAlert(String deviceId, String alertMessage) {
        createNotification(1L, "Security Alert", "Device " + deviceId + ": " + alertMessage, NotificationType.ALERT);
    }

    public void markAsRead(Long id) {
        Notification n = notificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notification not found"));
        n.setRead(true);
        notificationRepository.save(n);
    }
}