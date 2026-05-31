package com.ruansegrini.clinica_api.dto.request;

import com.ruansegrini.clinica_api.domain.enums.Specialty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorRequestDTO(

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address.")
        String email,

        @NotBlank(message = "Crm is required.")
        String crm,

        @NotNull(message = "Specialty is required.")
        Specialty specialty,

        @NotBlank(message = "Phone is required.")
        String phone
) {
}
