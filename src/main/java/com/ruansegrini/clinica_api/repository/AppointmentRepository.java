package com.ruansegrini.clinica_api.repository;

import com.ruansegrini.clinica_api.domain.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    boolean existsByDoctorIdAndDateTime(UUID doctorId, LocalDateTime dateTime);

    boolean existsByPatientIdAndDateTimeBetween(UUID patientId, LocalDateTime start, LocalDateTime end);
}
