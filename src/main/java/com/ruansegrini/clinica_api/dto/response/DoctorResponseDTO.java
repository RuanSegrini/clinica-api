package com.ruansegrini.clinica_api.dto.response;

import com.ruansegrini.clinica_api.domain.entity.Doctor;
import com.ruansegrini.clinica_api.domain.enums.Specialty;

import java.time.LocalDateTime;
import java.util.UUID;

public record DoctorResponseDTO (
        UUID id,
        String name,
        String email,
        String crm,
        Specialty specialty,
        Boolean active,
        String phone,
        LocalDateTime createdAt
){

    public static DoctorResponseDTO from(Doctor doctor){
        return new DoctorResponseDTO(
                doctor.getId(),
                doctor.getName(),
                doctor.getEmail(),
                doctor.getCrm(),
                doctor.getSpecialty(),
                doctor.getActive(),
                doctor.getPhone(),
                doctor.getCreatedAt()
        );
    }
}
