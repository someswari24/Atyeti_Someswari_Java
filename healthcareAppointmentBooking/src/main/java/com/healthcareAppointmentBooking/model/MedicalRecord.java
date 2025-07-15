package com.healthcareAppointmentBooking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id",nullable = false)
    private User patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id",nullable = false)
    private  User doctor;

    @Column(nullable = false)
    private String diagnosis;

    @Column(nullable = false)
    private String prescription;
}
