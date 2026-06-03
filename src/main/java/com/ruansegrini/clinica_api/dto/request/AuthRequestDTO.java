package com.ruansegrini.clinica_api.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDTO(
        @NotBlank
        String login,
        @NotBlank
        String password
) {}
