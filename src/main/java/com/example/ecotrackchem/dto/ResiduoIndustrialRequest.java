package com.example.ecotrackchem.dto;

import java.math.BigDecimal;

import com.example.ecotrackchem.util.DisposicionFinal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ResiduoIndustrialRequest(
        @NotBlank @Size(max = 100) String tipoResiduo,

        @NotNull @Positive BigDecimal volumenLitros,

        @NotNull DisposicionFinal disposicionFinal,

        @NotNull Boolean requiereCertificado,

        @NotNull(message = "Debe indicar el id del lote que genero el residuo")
        Integer loteId) {
}
