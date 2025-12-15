package com.banco.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad Cuenta - Representa una cuenta bancaria
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(name = "numero_cuenta", nullable = false, unique = true)
    private String numeroCuenta;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal saldo;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDate fechaApertura;
}
