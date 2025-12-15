package com.banco.demo.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "Resultado del cálculo de cuota de préstamo")
public class CalculoCuotaResponse {

    @Schema(description = "Cuota mensual a pagar en COP", example = "320500.50")
    private BigDecimal cuotaMensual;
    
    @Schema(description = "Tasa de interés anual aplicada", example = "10.0")
    private Double tasaInteres;
    
    @Schema(description = "Total a pagar incluyendo intereses en COP", example = "11538000.00")
    private BigDecimal totalPagar;
}
