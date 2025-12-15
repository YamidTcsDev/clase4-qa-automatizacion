package com.banco.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO para respuesta de solicitud de préstamo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrestamoResponse {

    private String numeroSolicitud;
    private String estado;
    private LocalDateTime fechaCreacion;
}
