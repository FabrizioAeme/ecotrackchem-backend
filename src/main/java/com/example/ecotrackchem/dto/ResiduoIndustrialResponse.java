package com.example.ecotrackchem.dto;

import java.math.BigDecimal;

import com.example.ecotrackchem.util.DisposicionFinal;

public record ResiduoIndustrialResponse(
        Integer id,
        String tipoResiduo,
        BigDecimal volumenLitros,
        DisposicionFinal disposicionFinal,
        Boolean requiereCertificado,
        Integer loteId,
        String loteCodigo) {
}
