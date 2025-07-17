package com.healthcareAppointmentBooking.service;

import com.healthcareAppointmentBooking.model.Appointment;
import com.healthcareAppointmentBooking.model.AppointmentStatus;
import com.healthcareAppointmentBooking.model.User;
import com.healthcareAppointmentBooking.repository.AppointmentRepository;
import com.healthcareAppointmentBooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public Appointment bookAppointment(Appointment appointment, String patientUsername) {
        User patient = userRepository.findByUsername(patientUsername)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        User doctor = userRepository.findById(appointment.getDoctor().getId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentStatus(AppointmentStatus.BOOKED);

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsForPatient(String patientUsername) {
        return appointmentRepository.findByPatientUsername(patientUsername);
    }

    public List<Appointment> getAppointmentsForDoctor(String doctorUsername) {
        return appointmentRepository.findByDoctorUsername(doctorUsername);
    }
}
