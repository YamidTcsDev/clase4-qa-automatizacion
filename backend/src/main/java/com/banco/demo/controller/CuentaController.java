package com.banco.demo.controller;

import com.banco.demo.dto.SaldoResponse;
import com.banco.demo.service.CuentaService;
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
public class CuentaController {

    private final CuentaService cuentaService;

    /**
     * GET /api/cuentas/saldo?userId={id}
     * Consulta el saldo de la cuenta principal del usuario
     * 
     * @param userId ID del usuario
     * @return Información de saldo
     */
    @GetMapping("/saldo")
    public ResponseEntity<SaldoResponse> consultarSaldo(@RequestParam Long userId) {
        log.info("📥 GET /api/cuentas/saldo?userId={}", userId);
        
        SaldoResponse response = cuentaService.obtenerSaldo(userId);
        
        log.info("✅ Saldo consultado - Cuenta: {}, Saldo: {}", 
                response.getNumeroCuenta(), response.getSaldo());
        return ResponseEntity.ok(response);
    }
}
