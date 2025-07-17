package com.healthcareAppointmentBooking.controller;

import com.healthcareAppointmentBooking.model.Appointment;
import com.healthcareAppointmentBooking.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    @PreAuthorize("hasRole('PATIENT')")
    public ResponseEntity<Appointment> bookAppointment(@RequestBody Appointment appointment, Principal principal) {
        Appointment saved = appointmentService.bookAppointment(appointment, principal.getName());
        return ResponseEntity.ok(saved);
    }

    @GetMapping("/mine")
    @PreAuthorize("hasRole('PATIENT')")
    public List<Appointment> getMyAppointments(Principal principal) {
        return appointmentService.getAppointmentsForPatient(principal.getName());
    }

    @GetMapping("/doctor")
    @PreAuthorize("hasRole('DOCTOR')")
    public List<Appointment> getDoctorAppointments(Principal principal) {
        return appointmentService.getAppointmentsForDoctor(principal.getName());
    }
}

