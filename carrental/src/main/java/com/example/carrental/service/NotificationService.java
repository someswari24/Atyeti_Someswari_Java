package com.example.carrental.service;

import com.example.carrental.model.Notification;
import com.example.carrental.model.User;
import com.example.carrental.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Notification createNotification(Notification notification){
        log.info("Creating Notification :"+notification.getMessage());
        return notificationRepository.save(notification);
    }

    public List<Notification> findByUser(User user){
        return notificationRepository.findByUser(user);
    }

    public List<Notification> findAllOrderedByDate(){
        return notificationRepository.findAllOrderedByDate();
    }
}
