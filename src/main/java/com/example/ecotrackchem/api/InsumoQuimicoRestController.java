package com.example.ecotrackchem.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecotrackchem.dto.InsumoQuimicoRequest;
import com.example.ecotrackchem.dto.InsumoQuimicoResponse;
import com.example.ecotrackchem.service.InsumoQuimicoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/insumos")
@RequiredArgsConstructor
public class InsumoQuimicoRestController {

    private final InsumoQuimicoService service;

    @GetMapping
    public ResponseEntity<List<InsumoQuimicoResponse>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InsumoQuimicoResponse> verDetalle(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<InsumoQuimicoResponse> crear(@Valid @RequestBody InsumoQuimicoRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InsumoQuimicoResponse> actualizar(@PathVariable Integer id,
            @Valid @RequestBody InsumoQuimicoRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
