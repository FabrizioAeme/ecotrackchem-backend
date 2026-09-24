package com.example.ecotrackchem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank @Email String correo,
        @NotBlank String clave) {
}
