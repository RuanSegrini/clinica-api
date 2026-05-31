package com.ruansegrini.clinica_api.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PatientRequestDTO(

        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        String phone,

        @NotBlank(message = "CPF é obrigatório")
        @Size(min = 11, max = 11, message = "CPF deve ter 11 dígitos")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas números")
        String cpf
) {}
