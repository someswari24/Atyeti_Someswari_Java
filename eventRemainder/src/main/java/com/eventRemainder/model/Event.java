package com.eventRemainder.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "events")
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @Column(name = "eventTime")
    private LocalDateTime eventTime;
    @Column(name = "userEmail")
    private String userEmail;
    private boolean remainderSent=false;

}
