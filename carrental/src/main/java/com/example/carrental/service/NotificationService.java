package com.example.carrental.service;

import com.example.carrental.model.Notification;
import com.example.carrental.model.User;
import com.example.carrental.repository.NotificationRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notification){
        return notificationRepository.save(notification);
    }

    public List<Notification> findByUser(User user){
        return notificationRepository.findByUser(user);
    }

    public List<Notification> findAllOrderedByDate(){
        return notificationRepository.findAllOrderedByDate();
    }
}
