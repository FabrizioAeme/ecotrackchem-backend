package com.example.ecotrackchem.dto;

import java.time.LocalDate;
import java.math.BigDecimal;

import com.example.ecotrackchem.util.EstadoLote;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record LoteRequest(
        @NotBlank @Size(max = 30) String codigoLote,

        @NotNull @PastOrPresent(message = "La fecha de fabricacion no puede ser futura")
        LocalDate fechaFabricacion,

        @NotNull @Positive BigDecimal volumenProducido,

        @NotNull EstadoLote estado,

        @NotNull(message = "Debe indicar el id del usuario que autoriza el lote")
        Integer usuarioId,

        @NotNull(message = "Debe indicar el id del insumo quimico principal")
        Integer insumoId) {
}
