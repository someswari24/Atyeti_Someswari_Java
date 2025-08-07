package com.eventRemainder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.eventRemainder.model.Event;
import com.eventRemainder.repository.EventRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void sendReminders() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime in30Minutes = now.plusMinutes(30);

        List<Event> events = eventRepository.findByRemainderSentFalseAndEventTimeBetween(now, in30Minutes);

        for (Event event : events) {
            emailService.sendRemainder(event);
            event.setRemainderSent(true);
            eventRepository.save(event);

            System.out.println("Checking for upcoming events at: " + now);
            System.out.println("Found event: " + event.getTitle());
        }

    }


    public Event saveEvent(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }
}
