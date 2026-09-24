package com.example.ecotrackchem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegistroRequest(
        @NotBlank @Size(max = 100) String nombres,
        @NotBlank @Size(max = 100) String apellidos,
        @NotBlank @Email String correo,
        @NotBlank @Size(min = 6, message = "La clave debe tener al menos 6 caracteres") String clave) {
}
