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

//Consulta usuarios ya registrados en la plataforma. Solo accesible para usuarios con rol ADMINISTRADOR//
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
