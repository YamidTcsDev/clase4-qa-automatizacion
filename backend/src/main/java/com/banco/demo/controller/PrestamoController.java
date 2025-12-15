package com.banco.demo.controller;

import com.banco.demo.dto.*;
import com.banco.demo.service.PrestamoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de préstamos
 * Endpoint: /api/prestamos
 */
@RestController
@RequestMapping("/api/prestamos")
@RequiredArgsConstructor
@Slf4j
public class PrestamoController {

    private final PrestamoService prestamoService;

    /**
     * POST /api/prestamos/calcular
     * Calcula la cuota mensual de un préstamo
     * 
     * @param request Monto y plazo del préstamo
     * @return Cuota mensual, tasa de interés y total a pagar
     */
    @PostMapping("/calcular")
    public ResponseEntity<CalculoCuotaResponse> calcularCuota(@Valid @RequestBody CalculoCuotaRequest request) {
        log.info("📥 POST /api/prestamos/calcular - Monto: {}, Plazo: {} meses", 
                request.getMonto(), request.getPlazoMeses());
        
        CalculoCuotaResponse response = prestamoService.calcularCuota(request);
        
        log.info("✅ Cuota calculada: {}", response.getCuotaMensual());
        return ResponseEntity.ok(response);
    }

    /**
     * POST /api/prestamos/solicitar
     * Crea una nueva solicitud de préstamo
     * 
     * @param request Datos de la solicitud
     * @return Número de solicitud y estado
     */
    @PostMapping("/solicitar")
    public ResponseEntity<PrestamoResponse> solicitarPrestamo(@Valid @RequestBody PrestamoRequest request) {
        log.info("📥 POST /api/prestamos/solicitar - Usuario: {}, Monto: {}", 
                request.getUserId(), request.getMonto());
        
        PrestamoResponse response = prestamoService.solicitarPrestamo(request);
        
        log.info("✅ Solicitud creada: {}", response.getNumeroSolicitud());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * GET /api/prestamos/solicitudes?userId={id}
     * Obtiene todas las solicitudes de préstamo de un usuario
     * 
     * @param userId ID del usuario
     * @return Lista de solicitudes
     */
    @GetMapping("/solicitudes")
    public ResponseEntity<List<SolicitudDto>> obtenerSolicitudes(@RequestParam Long userId) {
        log.info("📥 GET /api/prestamos/solicitudes?userId={}", userId);
        
        List<SolicitudDto> solicitudes = prestamoService.obtenerSolicitudes(userId);
        
        log.info("✅ {} solicitudes encontradas", solicitudes.size());
        return ResponseEntity.ok(solicitudes);
    }
}
