package com.eventRemainder.repository;
import com.eventRemainder.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByRemainderSentFalseAndEventTimeBetween(LocalDateTime from, LocalDateTime to);

}