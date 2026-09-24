package com.example.ecotrackchem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecotrackchem.dto.InsumoQuimicoRequest;
import com.example.ecotrackchem.dto.InsumoQuimicoResponse;
import com.example.ecotrackchem.exception.ResourceNotFoundException;
import com.example.ecotrackchem.mapper.InsumoQuimicoMapper;
import com.example.ecotrackchem.model.InsumoQuimico;
import com.example.ecotrackchem.repository.InsumoQuimicoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsumoQuimicoService {

    private final InsumoQuimicoRepository repository;
    private final InsumoQuimicoMapper mapper;

    @Transactional(readOnly = true)
    public List<InsumoQuimicoResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public InsumoQuimicoResponse getById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo quimico no encontrado con id " + id));
    }

    @Transactional
    public InsumoQuimicoResponse create(InsumoQuimicoRequest request) {
        InsumoQuimico entity = mapper.toEntity(request);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public InsumoQuimicoResponse update(Integer id, InsumoQuimicoRequest request) {
        InsumoQuimico entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo quimico no encontrado con id " + id));
        mapper.actualizarEntity(entity, request);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Insumo quimico no encontrado con id " + id);
        }
        repository.deleteById(id);
    }
}
