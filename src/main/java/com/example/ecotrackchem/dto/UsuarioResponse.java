package com.example.ecotrackchem.dto;

import com.example.ecotrackchem.util.RolUsuario;

public record UsuarioResponse(
        Integer id,
        String nombres,
        String apellidos,
        String correo,
        RolUsuario rol) {
}
