package com.banco.demo.controller;

import com.banco.demo.dto.*;
import com.banco.demo.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Préstamos", description = "Endpoints para cálculo, solicitud y consulta de préstamos")
public class PrestamoController {

    private final PrestamoService prestamoService;

    /**
     * POST /api/prestamos/calcular
     * Calcula la cuota mensual de un préstamo
     * 
     * @param request Monto y plazo del préstamo
     * @return Cuota mensual, tasa de interés y total a pagar
     */
    @Operation(
        summary = "Calcular cuota de préstamo",
        description = "Calcula la cuota mensual, tasa de interés aplicada y total a pagar " +
                "basándose en el monto solicitado, plazo en meses y propósito del préstamo. " +
                "Las tasas de interés varían según el propósito: " +
                "Vivienda (8%), Vehículo (10%), Personal (12%)."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Cálculo realizado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = CalculoCuotaResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de entrada inválidos (monto, plazo o propósito)",
            content = @Content(mediaType = "application/json")
        )
    })
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
    @Operation(
        summary = "Solicitar préstamo",
        description = "Crea una nueva solicitud de préstamo para un usuario. " +
                "La solicitud queda en estado PENDIENTE y genera un número único de solicitud. " +
                "Requiere datos del usuario, monto, plazo y propósito del préstamo."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Solicitud creada exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = PrestamoResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Datos de solicitud inválidos",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json")
        )
    })
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
    @Operation(
        summary = "Obtener solicitudes de préstamo",
        description = "Retorna el historial completo de solicitudes de préstamo de un usuario, " +
                "incluyendo información de monto, plazo, cuota mensual, propósito, estado y fecha. " +
                "Los estados posibles son: PENDIENTE, APROBADO, RECHAZADO."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Lista de solicitudes obtenida exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SolicitudDto.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "ID de usuario inválido",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario no encontrado",
            content = @Content(mediaType = "application/json")
        )
    })
    @GetMapping("/solicitudes")
    public ResponseEntity<List<SolicitudDto>> obtenerSolicitudes(
            @Parameter(description = "ID del usuario para consultar sus solicitudes", required = true, example = "1")
            @RequestParam Long userId) {
        log.info("📥 GET /api/prestamos/solicitudes?userId={}", userId);
        
        List<SolicitudDto> solicitudes = prestamoService.obtenerSolicitudes(userId);
        
        log.info("✅ {} solicitudes encontradas", solicitudes.size());
        return ResponseEntity.ok(solicitudes);
    }
}
