package com.banco.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO para listar solicitudes de préstamo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {

    private String numeroSolicitud;
    private BigDecimal monto;
    private Integer plazoMeses;
    private BigDecimal cuotaMensual;
    private String estado;
    private LocalDate fechaSolicitud;
}
