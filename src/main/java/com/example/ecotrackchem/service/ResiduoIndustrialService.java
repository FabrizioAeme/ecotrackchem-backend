package com.example.ecotrackchem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecotrackchem.dto.ResiduoIndustrialRequest;
import com.example.ecotrackchem.dto.ResiduoIndustrialResponse;
import com.example.ecotrackchem.exception.ResourceNotFoundException;
import com.example.ecotrackchem.mapper.ResiduoIndustrialMapper;
import com.example.ecotrackchem.model.Lote;
import com.example.ecotrackchem.model.ResiduoIndustrial;
import com.example.ecotrackchem.repository.LoteRepository;
import com.example.ecotrackchem.repository.ResiduoIndustrialRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResiduoIndustrialService {

    private final ResiduoIndustrialRepository repository;
    private final LoteRepository loteRepository;
    private final ResiduoIndustrialMapper mapper;

    @Transactional(readOnly = true)
    public List<ResiduoIndustrialResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ResiduoIndustrialResponse getById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Residuo industrial no encontrado con id " + id));
    }

    @Transactional
    public ResiduoIndustrialResponse create(ResiduoIndustrialRequest request) {
        Lote lote = buscarLote(request.loteId());
        ResiduoIndustrial entity = mapper.toEntity(request, lote);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public ResiduoIndustrialResponse update(Integer id, ResiduoIndustrialRequest request) {
        ResiduoIndustrial entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Residuo industrial no encontrado con id " + id));
        Lote lote = buscarLote(request.loteId());
        mapper.actualizarEntity(entity, request, lote);
        return mapper.toDto(repository.save(entity));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Residuo industrial no encontrado con id " + id);
        }
        repository.deleteById(id);
    }

    private Lote buscarLote(Integer id) {
        return loteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado con id " + id));
    }
}
