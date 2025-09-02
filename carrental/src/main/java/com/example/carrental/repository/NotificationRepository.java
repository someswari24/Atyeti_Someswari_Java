package com.example.carrental.repository;

import com.example.carrental.model.Notification;
import com.example.carrental.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findByUser(User user);

    @Query("SELECT n FROM Notification n ORDER BY n.createdDate DESC")
    List<Notification> findAllOrderedByDate();
}
