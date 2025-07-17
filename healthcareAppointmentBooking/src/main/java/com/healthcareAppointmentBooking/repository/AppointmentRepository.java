package com.healthcareAppointmentBooking.repository;

import com.healthcareAppointmentBooking.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository <Appointment,Long>{
    List<Appointment> findByPatientUsername(String username);
    List<Appointment> findByDoctorUsername(String username);

}
