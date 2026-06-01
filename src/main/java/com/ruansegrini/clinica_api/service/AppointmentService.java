package com.ruansegrini.clinica_api.service;

import com.ruansegrini.clinica_api.domain.entity.Appointment;
import com.ruansegrini.clinica_api.domain.entity.Doctor;
import com.ruansegrini.clinica_api.domain.entity.Patient;
import com.ruansegrini.clinica_api.domain.enums.CancelReason;
import com.ruansegrini.clinica_api.dto.request.AppointmentRequestDTO;
import com.ruansegrini.clinica_api.dto.response.AppointmentResponseDTO;
import com.ruansegrini.clinica_api.repository.AppointmentRepository;
import com.ruansegrini.clinica_api.repository.DoctorRepository;
import com.ruansegrini.clinica_api.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public List<AppointmentResponseDTO> findAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(AppointmentResponseDTO::from)
                .toList();
    }

    public AppointmentResponseDTO findById(UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        return AppointmentResponseDTO.from(appointment);
    }

    public AppointmentResponseDTO create(AppointmentRequestDTO dto) {

        Patient patient = patientRepository.findById(dto.patientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        Doctor doctor = doctorRepository.findById(dto.doctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        boolean doctorBusy = appointmentRepository
                .existsByDoctorIdAndDateTime(dto.doctorId(), dto.dateTime());
        if (doctorBusy) {
            throw new IllegalStateException("Doctor already has an appointment at this time.");
        }

        boolean patientBusy = appointmentRepository
                .existsByPatientIdAndDateTimeBetween(
                        dto.patientId(),
                        dto.dateTime().toLocalDate().atStartOfDay(),
                        dto.dateTime().toLocalDate().atTime(23, 59, 59)
                );
        if (patientBusy) {
            throw new IllegalStateException("Patient already has an appointment on this day.");
        }

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setDateTime(dto.dateTime());
        appointment.setNotes(dto.notes());

        return AppointmentResponseDTO.from(appointmentRepository.save(appointment));
    }

    public AppointmentResponseDTO cancel(UUID id, CancelReason reason) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (appointment.isCompleted()) {
            throw new IllegalStateException("Cannot cancel a completed appointment.");
        }
        if (appointment.isCanceled()) {
            throw new IllegalStateException("Appointment is already canceled.");
        }

        appointment.cancel(reason);

        return AppointmentResponseDTO.from(appointmentRepository.save(appointment));
    }

    public AppointmentResponseDTO complete(UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (!appointment.isScheduled()) {
            throw new IllegalStateException("Only scheduled appointments can be completed.");
        }

        appointment.complete();

        return AppointmentResponseDTO.from(appointmentRepository.save(appointment));
    }

    public AppointmentResponseDTO markNoShow(UUID id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (!appointment.isScheduled()) {
            throw new IllegalStateException("Only scheduled appointments can be marked as no-show.");
        }

        appointment.markNoShow();

        return AppointmentResponseDTO.from(appointmentRepository.save(appointment));
    }
}