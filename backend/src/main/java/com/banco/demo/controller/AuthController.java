package com.banco.demo.controller;

import com.banco.demo.dto.LoginRequest;
import com.banco.demo.dto.LoginResponse;
import com.banco.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de autenticación
 * Endpoint: /api/auth
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    /**
     * POST /api/auth/login
     * Autentica un usuario con email y contraseña
     * 
     * @param request Credenciales de login
     * @return Token y datos del usuario
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("📥 POST /api/auth/login - Email: {}", request.getEmail());
        
        LoginResponse response = authService.login(request);
        
        log.info("✅ Login exitoso - Usuario: {}", response.getNombre());
        return ResponseEntity.ok(response);
    }
}
