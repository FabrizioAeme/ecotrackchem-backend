package com.example.ecotrackchem.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecotrackchem.dto.UsuarioResponse;
import com.example.ecotrackchem.service.UsuarioService;

import lombok.RequiredArgsConstructor;

/**
 * Consulta de usuarios ya registrados. El alta de usuarios se realiza
 * mediante POST /auth/register (no aqui), para mantener separado el
 * flujo de autenticacion del resto de la API de negocio.
 */
@RestController
@RequestMapping("api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioRestController {

    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {
        return ResponseEntity.ok(service.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> verDetalle(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getUsuarioById(id));
    }
}
