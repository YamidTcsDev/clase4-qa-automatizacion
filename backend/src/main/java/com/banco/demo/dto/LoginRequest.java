package com.banco.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para petición de login
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Credenciales de autenticación del usuario")
public class LoginRequest {

    @Schema(description = "Correo electrónico del usuario", example = "qa.tester@banco.com", required = true)
    @NotBlank(message = "El email es requerido")
    @Email(message = "Formato de email inválido")
    private String email;

    @Schema(description = "Contraseña del usuario", example = "password123", required = true)
    @NotBlank(message = "La contraseña es requerida")
    private String password;
}
