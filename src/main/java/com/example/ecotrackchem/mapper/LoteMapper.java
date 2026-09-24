package com.example.ecotrackchem.mapper;

import org.springframework.stereotype.Component;

import com.example.ecotrackchem.dto.LoteResponse;
import com.example.ecotrackchem.model.Lote;

@Component
public class LoteMapper {

    public LoteResponse toDto(Lote entity) {
        return new LoteResponse(
                entity.getId(),
                entity.getCodigoLote(),
                entity.getFechaFabricacion(),
                entity.getVolumenProducido(),
                entity.getEstado(),
                entity.getUsuario().getId(),
                entity.getUsuario().getNombres() + " " + entity.getUsuario().getApellidos(),
                entity.getInsumoPrincipal().getId(),
                entity.getInsumoPrincipal().getNombre());
    }
}
