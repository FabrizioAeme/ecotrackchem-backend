package com.example.ecotrackchem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.ecotrackchem.util.EstadoLote;

public record LoteResponse(
        Integer id,
        String codigoLote,
        LocalDate fechaFabricacion,
        BigDecimal volumenProducido,
        EstadoLote estado,
        Integer usuarioId,
        String usuarioNombre,
        Integer insumoId,
        String insumoNombre) {
}
