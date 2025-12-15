package com.banco.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO para respuesta de consulta de saldo
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaldoResponse {

    private String numeroCuenta;
    private BigDecimal saldo;
    private String moneda;
    private String tipoCuenta;
}
