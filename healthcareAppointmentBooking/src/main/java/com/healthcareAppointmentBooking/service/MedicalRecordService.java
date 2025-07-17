package com.healthcareAppointmentBooking.service;

import com.healthcareAppointmentBooking.model.MedicalRecord;
import com.healthcareAppointmentBooking.model.User;
import com.healthcareAppointmentBooking.repository.MedicalRecordRepository;
import com.healthcareAppointmentBooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final UserRepository userRepository;

    public MedicalRecord saveOrUpdate(MedicalRecord record, String doctorUsername) {
        User doctor = userRepository.findByUsername(doctorUsername)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        User patient = userRepository.findById(record.getPatient().getId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        record.setDoctor(doctor);
        record.setPatient(patient);

        return medicalRecordRepository.save(record);
    }

    public Optional<MedicalRecord> getRecordByPatientId(Long patientId) {
        return medicalRecordRepository.findByPatientId(patientId);
    }
}

