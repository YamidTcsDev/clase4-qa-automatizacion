package com.banco.demo.controller;

import com.banco.demo.dto.LoginRequest;
import com.banco.demo.dto.LoginResponse;
import com.banco.demo.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Autenticación", description = "Endpoints para autenticación y gestión de sesiones de usuario")
public class AuthController {

    private final AuthService authService;

    /**
     * POST /api/auth/login
     * Autentica un usuario con email y contraseña
     * 
     * @param request Credenciales de login
     * @return Token y datos del usuario
     */
    @Operation(
        summary = "Iniciar sesión",
        description = "Autentica un usuario mediante email y contraseña. " +
                "Retorna un token JWT para autenticación en peticiones subsecuentes y los datos del usuario."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Login exitoso",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = LoginResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Credenciales inválidas o datos faltantes",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json")
        )
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        log.info("📥 POST /api/auth/login - Email: {}", request.getEmail());
        
        LoginResponse response = authService.login(request);
        
        log.info("✅ Login exitoso - Usuario: {}", response.getNombre());
        return ResponseEntity.ok(response);
    }
}
