package com.example.carrental.controller;

import com.example.carrental.model.Notification;
import com.example.carrental.model.User;
import com.example.carrental.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        Notification savedNotification = notificationService.createNotification(notification);
        log.info("Notification created with ID: {}", savedNotification.getId());
        return ResponseEntity.ok(savedNotification);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Notification>> getNotificationsByUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        List<Notification> notifications = notificationService.findByUser(user);

        if (notifications.isEmpty()) {
            log.warn("No notifications found for user ID: {}", userId);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/all")
    public List<Notification> getAllNotifications() {
        log.info("Fetching all notifications ordered by date");
        return notificationService.findAllOrderedByDate();
    }
}
