package com.banco.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO para respuesta de cálculo de cuota
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoCuotaResponse {

    private BigDecimal cuotaMensual;
    private Double tasaInteres;
    private BigDecimal totalPagar;
}
