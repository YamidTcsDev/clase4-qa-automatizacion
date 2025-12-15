package com.banco.demo.controller;

import com.banco.demo.dto.SaldoResponse;
import com.banco.demo.service.CuentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de cuentas bancarias
 * Endpoint: /api/cuentas
 */
@RestController
@RequestMapping("/api/cuentas")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Cuentas", description = "Endpoints para consulta de información de cuentas bancarias")
public class CuentaController {

    private final CuentaService cuentaService;

    /**
     * GET /api/cuentas/saldo?userId={id}
     * Consulta el saldo de la cuenta principal del usuario
     * 
     * @param userId ID del usuario
     * @return Información de saldo
     */
    @Operation(
        summary = "Consultar saldo",
        description = "Obtiene el saldo actual de la cuenta principal del usuario, " +
                "incluyendo el número de cuenta, tipo de cuenta y saldo disponible."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Saldo consultado exitosamente",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SaldoResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "ID de usuario inválido",
            content = @Content(mediaType = "application/json")
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Usuario o cuenta no encontrada",
            content = @Content(mediaType = "application/json")
        )
    })
    @GetMapping("/saldo")
    public ResponseEntity<SaldoResponse> consultarSaldo(
            @Parameter(description = "ID del usuario propietario de la cuenta", required = true, example = "1")
            @RequestParam Long userId) {
        log.info("📥 GET /api/cuentas/saldo?userId={}", userId);
        
        SaldoResponse response = cuentaService.obtenerSaldo(userId);
        
        log.info("✅ Saldo consultado - Cuenta: {}, Saldo: {}", 
                response.getNumeroCuenta(), response.getSaldo());
        return ResponseEntity.ok(response);
    }
}
