package com.example.ecotrackchem.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ecotrackchem.dto.UsuarioResponse;
import com.example.ecotrackchem.exception.ResourceNotFoundException;
import com.example.ecotrackchem.mapper.UsuarioMapper;
import com.example.ecotrackchem.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

/**
 * Ademas de exponer las consultas de negocio sobre Usuario (listar / ver
 * detalle), implementa UserDetailsService: es la pieza que Spring
 * Security usa para cargar el usuario autenticado a partir del correo
 * durante el login (ver AuthService / JwtAuthFilter).
 */
@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        return repository.findByCorreo(correo)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + correo));
    }

    public List<UsuarioResponse> getAllUsuarios() {
        return repository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    public UsuarioResponse getUsuarioById(Integer id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con id " + id));
    }
}
