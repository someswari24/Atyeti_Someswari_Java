package com.healthcareAppointmentBooking.controller;

import com.healthcareAppointmentBooking.model.MedicalRecord;
import com.healthcareAppointmentBooking.service.MedicalRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    public ResponseEntity<MedicalRecord> saveRecord(@RequestBody MedicalRecord record, Principal principal) {
        return ResponseEntity.ok(medicalRecordService.saveOrUpdate(record, principal.getName()));
    }

    @GetMapping("/{patientId}")
    @PreAuthorize("hasAnyRole('DOCTOR', 'PATIENT')")
    public ResponseEntity<MedicalRecord> getRecord(@PathVariable Long patientId, Principal principal) {
        // Optional: add ownership check if needed
        return medicalRecordService.getRecordByPatientId(patientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}

