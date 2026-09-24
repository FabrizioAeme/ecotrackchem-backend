package com.example.ecotrackchem.dto;

import java.math.BigDecimal;

import com.example.ecotrackchem.util.NivelPeligrosidad;

public record InsumoQuimicoResponse(
        Integer id,
        String codigoCas,
        String nombre,
        BigDecimal nivelPureza,
        NivelPeligrosidad nivelPeligrosidad,
        BigDecimal stockDisponible) {
}
