package com.ruansegrini.clinica_api.dto.response;

import com.ruansegrini.clinica_api.domain.entity.Appointment;
import com.ruansegrini.clinica_api.domain.enums.AppointmentStatus;
import com.ruansegrini.clinica_api.domain.enums.CancelReason;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentResponseDTO(
        UUID id,
        String patientName,
        String doctorName,
        LocalDateTime dateTime,
        AppointmentStatus status,
        CancelReason cancelReason,
        String notes,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static AppointmentResponseDTO from(Appointment appointment) {
        return new AppointmentResponseDTO(
                appointment.getId(),
                appointment.getPatient().getName(),
                appointment.getDoctor().getName(),
                appointment.getDateTime(),
                appointment.getStatus(),
                appointment.getCancelReason(),
                appointment.getNotes(),
                appointment.getCreatedAt(),
                appointment.getUpdatedAt()
        );
    }
}