package com.ruansegrini.clinica_api.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentRequestDTO(

        @NotNull(message = "Patient is required.")
        UUID patientId,

        @NotNull(message = "Doctor is required.")
        UUID doctorId,

        @NotNull(message = "Date and time is required.")
        @Future(message = "Appointment date must be in the future.")
        LocalDateTime dateTime,

        String notes
) {}
