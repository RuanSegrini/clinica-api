package com.ruansegrini.clinica_api.dto.response;

import com.ruansegrini.clinica_api.domain.entity.Patient;

import java.time.LocalDateTime;
import java.util.UUID;

public record PatientResponseDTO(
        UUID id,
        String name,
        String email,
        String phone,
        String cpf,
        Boolean active,
        LocalDateTime createdAt
) {
    public static PatientResponseDTO from(Patient patient) {
        return new PatientResponseDTO(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getPhone(),
                patient.getCpf(),
                patient.getActive(),
                patient.getCreatedAt()
        );
    }
}