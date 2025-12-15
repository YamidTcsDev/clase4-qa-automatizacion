package com.banco.demo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO para petición de cálculo de cuota
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculoCuotaRequest {

    @NotNull(message = "El monto es requerido")
    @Min(value = 1000000, message = "El monto mínimo es $1,000,000")
    @Max(value = 50000000, message = "El monto máximo es $50,000,000")
    private BigDecimal monto;

    @NotNull(message = "El plazo es requerido")
    @Min(value = 12, message = "El plazo mínimo es 12 meses")
    @Max(value = 48, message = "El plazo máximo es 48 meses")
    private Integer plazoMeses;
}
