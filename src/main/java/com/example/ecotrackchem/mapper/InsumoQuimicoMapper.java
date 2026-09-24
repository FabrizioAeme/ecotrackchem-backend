package com.example.ecotrackchem.mapper;

import org.springframework.stereotype.Component;

import com.example.ecotrackchem.dto.InsumoQuimicoRequest;
import com.example.ecotrackchem.dto.InsumoQuimicoResponse;
import com.example.ecotrackchem.model.InsumoQuimico;

@Component
public class InsumoQuimicoMapper {

    public InsumoQuimico toEntity(InsumoQuimicoRequest dto) {
        return InsumoQuimico.builder()
                .codigoCas(dto.codigoCas())
                .nombre(dto.nombre())
                .nivelPureza(dto.nivelPureza())
                .nivelPeligrosidad(dto.nivelPeligrosidad())
                .stockDisponible(dto.stockDisponible())
                .build();
    }

    public void actualizarEntity(InsumoQuimico entity, InsumoQuimicoRequest dto) {
        entity.setCodigoCas(dto.codigoCas());
        entity.setNombre(dto.nombre());
        entity.setNivelPureza(dto.nivelPureza());
        entity.setNivelPeligrosidad(dto.nivelPeligrosidad());
        entity.setStockDisponible(dto.stockDisponible());
    }

    public InsumoQuimicoResponse toDto(InsumoQuimico entity) {
        return new InsumoQuimicoResponse(
                entity.getId(),
                entity.getCodigoCas(),
                entity.getNombre(),
                entity.getNivelPureza(),
                entity.getNivelPeligrosidad(),
                entity.getStockDisponible());
    }
}
