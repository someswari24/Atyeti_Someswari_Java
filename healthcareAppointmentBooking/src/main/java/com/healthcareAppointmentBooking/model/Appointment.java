package com.healthcareAppointmentBooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    private User doctor;

    private LocalDateTime appointmentDateTime;

    @Enumerated(EnumType.STRING)
    private  AppointmentStatus appointmentStatus;

    private String reason;
}
