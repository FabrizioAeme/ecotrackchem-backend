package com.example.ecotrackchem.dto;

import java.math.BigDecimal;

import com.example.ecotrackchem.util.NivelPeligrosidad;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record InsumoQuimicoRequest(
        @NotBlank
        @Pattern(regexp = "^\\d{2,7}-\\d{2}-\\d$", message = "El codigo CAS debe tener el formato NN-NN-N, ej. 7732-18-5")
        String codigoCas,

        @NotBlank @Size(max = 150) String nombre,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = true)
        @DecimalMax(value = "100.0", inclusive = true, message = "El nivel de pureza es un porcentaje entre 0 y 100")
        BigDecimal nivelPureza,

        @NotNull NivelPeligrosidad nivelPeligrosidad,

        @NotNull @PositiveOrZero BigDecimal stockDisponible) {
}
