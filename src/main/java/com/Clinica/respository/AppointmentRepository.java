package com.Clinica.respository;

import com.Clinica.entity.Appointment;
import com.Clinica.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}