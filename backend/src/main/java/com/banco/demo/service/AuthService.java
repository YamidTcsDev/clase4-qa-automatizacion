package com.banco.demo.service;

import com.banco.demo.dto.LoginRequest;
import com.banco.demo.dto.LoginResponse;
import com.banco.demo.model.Usuario;
import com.banco.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.UUID;

/**
 * Servicio de autenticación
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    /**
     * Autentica un usuario con email y contraseña
     * @param request Credenciales de login
     * @return Respuesta con token y datos del usuario
     */
    public LoginResponse login(LoginRequest request) {
        log.info("Intento de login para usuario: {}", request.getEmail());
        
        Usuario usuario = usuarioRepository.findByEmailAndPassword(request.getEmail(), request.getPassword())
                .orElseThrow(() -> {
                    log.warn("Login fallido para usuario: {}", request.getEmail());
                    return new RuntimeException("Credenciales inválidas");
                });

        // Generar token simple (en producción usar JWT)
        String token = generateSimpleToken(usuario);
        
        log.info("Login exitoso para usuario: {} (ID: {})", usuario.getNombre(), usuario.getId());
        
        return new LoginResponse(token, usuario.getNombre(), usuario.getId());
    }

    /**
     * Genera un token simple para el usuario
     * @param usuario Usuario autenticado
     * @return Token de sesión
     */
    private String generateSimpleToken(Usuario usuario) {
        String rawToken = usuario.getId() + ":" + usuario.getEmail() + ":" + UUID.randomUUID();
        return Base64.getEncoder().encodeToString(rawToken.getBytes());
    }
}
