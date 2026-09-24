package com.example.ecotrackchem.mapper;

import org.springframework.stereotype.Component;

import com.example.ecotrackchem.dto.UsuarioResponse;
import com.example.ecotrackchem.model.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioResponse toDto(Usuario entity) {
        return new UsuarioResponse(
                entity.getId(),
                entity.getNombres(),
                entity.getApellidos(),
                entity.getCorreo(),
                entity.getRol());
    }
}
