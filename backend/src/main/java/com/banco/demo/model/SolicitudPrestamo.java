package com.banco.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Entidad SolicitudPrestamo - Representa una solicitud de préstamo
 */
@Entity
@Table(name = "solicitud_prestamo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudPrestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    @Column(name = "plazo_meses", nullable = false)
    private Integer plazoMeses;

    @Column(nullable = false)
    private String proposito;

    @Column(name = "cuota_mensual", nullable = false, precision = 15, scale = 2)
    private BigDecimal cuotaMensual;

    @Column(nullable = false)
    private String estado;

    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDate fechaSolicitud;

    @Column(name = "numero_solicitud", unique = true)
    private String numeroSolicitud;
}
