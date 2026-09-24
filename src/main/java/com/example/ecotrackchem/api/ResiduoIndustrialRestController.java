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

import com.example.ecotrackchem.dto.ResiduoIndustrialRequest;
import com.example.ecotrackchem.dto.ResiduoIndustrialResponse;
import com.example.ecotrackchem.service.ResiduoIndustrialService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/residuos")
@RequiredArgsConstructor
public class ResiduoIndustrialRestController {

    private final ResiduoIndustrialService service;

    @GetMapping
    public ResponseEntity<List<ResiduoIndustrialResponse>> listar() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResiduoIndustrialResponse> verDetalle(@PathVariable Integer id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ResiduoIndustrialResponse> crear(@Valid @RequestBody ResiduoIndustrialRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResiduoIndustrialResponse> actualizar(@PathVariable Integer id,
            @Valid @RequestBody ResiduoIndustrialRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
