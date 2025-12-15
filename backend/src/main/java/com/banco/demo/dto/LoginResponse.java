package com.banco.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para respuesta de login exitoso
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de autenticación exitosa")
public class LoginResponse {

    @Schema(description = "Token JWT para autenticación", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;
    
    @Schema(description = "Nombre completo del usuario", example = "QA Tester")
    private String nombre;
    
    @Schema(description = "ID único del usuario", example = "1")
    private Long userId;
}
