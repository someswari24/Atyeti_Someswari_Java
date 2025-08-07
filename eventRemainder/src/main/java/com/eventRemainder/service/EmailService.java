package com.eventRemainder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eventRemainder.model.Event;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRemainder(Event event) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(event.getUserEmail());
        message.setSubject("Remainder: " + event.getTitle());
        message.setText("Don't forget about your event! " +
                        "Title: " + event.getTitle() +
        " Description: " + event.getDescription() +
        " Scheduled for: " + event.getEventTime());
        mailSender.send(message);
    }
}
