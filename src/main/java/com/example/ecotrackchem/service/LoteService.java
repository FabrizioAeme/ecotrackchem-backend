package com.example.ecotrackchem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecotrackchem.dto.LoteRequest;
import com.example.ecotrackchem.dto.LoteResponse;
import com.example.ecotrackchem.exception.ResourceNotFoundException;
import com.example.ecotrackchem.mapper.LoteMapper;
import com.example.ecotrackchem.model.InsumoQuimico;
import com.example.ecotrackchem.model.Lote;
import com.example.ecotrackchem.model.Usuario;
import com.example.ecotrackchem.repository.InsumoQuimicoRepository;
import com.example.ecotrackchem.repository.LoteRepository;
import com.example.ecotrackchem.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoteService {

    private final LoteRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final InsumoQuimicoRepository insumoRepository;
    private final LoteMapper mapper;

    @Transactional(readOnly = true)
    public List<LoteResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public LoteResponse getById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado con id " + id));
    }

    @Transactional
    public LoteResponse create(LoteRequest request) {
        Usuario usuario = buscarUsuario(request.usuarioId());
        InsumoQuimico insumo = buscarInsumo(request.insumoId());

        Lote lote = Lote.builder()
                .codigoLote(request.codigoLote())
                .fechaFabricacion(request.fechaFabricacion())
                .volumenProducido(request.volumenProducido())
                .estado(request.estado())
                .usuario(usuario)
                .insumoPrincipal(insumo)
                .build();

        return mapper.toDto(repository.save(lote));
    }

    @Transactional
    public LoteResponse update(Integer id, LoteRequest request) {
        Lote lote = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lote no encontrado con id " + id));

        lote.setCodigoLote(request.codigoLote());
        lote.setFechaFabricacion(request.fechaFabricacion());
        lote.setVolumenProducido(request.volumenProducido());
        lote.setEstado(request.estado());
        lote.setUsuario(buscarUsuario(request.usuarioId()));
        lote.setInsumoPrincipal(buscarInsumo(request.insumoId()));

        return mapper.toDto(repository.save(lote));
    }

    @Transactional
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Lote no encontrado con id " + id);
        }
        repository.deleteById(id);
    }

    private Usuario buscarUsuario(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id " + id));
    }

    private InsumoQuimico buscarInsumo(Integer id) {
        return insumoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insumo quimico no encontrado con id " + id));
    }
}
