package com.example.ecotrackchem.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecotrackchem.dto.AuthRequest;
import com.example.ecotrackchem.dto.AuthResponse;
import com.example.ecotrackchem.dto.RegistroRequest;
import com.example.ecotrackchem.dto.RegistroResponse;
import com.example.ecotrackchem.model.Usuario;
import com.example.ecotrackchem.repository.UsuarioRepository;
import com.example.ecotrackchem.util.RolUsuario;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegistroResponse register(RegistroRequest request) {
        if (userRepository.findByCorreo(request.correo()).isPresent()) {
            return new RegistroResponse(request.correo(), "WARNING", "El usuario ya se encuentra registrado.");
        }
        var user = Usuario.builder()
                .nombres(request.nombres())
                .apellidos(request.apellidos())
                .correo(request.correo())
                .clave(passwordEncoder.encode(request.clave()))
                .rol(RolUsuario.OPERADOR_LABORATORIO)
                .build();
        userRepository.save(user);
        return new RegistroResponse(request.correo(), "INFORMATION", "Usuario registrado correctamente.");
    }

    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.correo(), request.clave()));
        var user = userRepository.findByCorreo(request.correo()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        return new AuthResponse(jwtToken, refreshToken);
    }
}
