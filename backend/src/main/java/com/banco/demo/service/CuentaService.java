package com.banco.demo.service;

import com.banco.demo.dto.SaldoResponse;
import com.banco.demo.model.Cuenta;
import com.banco.demo.repository.CuentaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Servicio de gestión de cuentas bancarias
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CuentaService {

    private final CuentaRepository cuentaRepository;

    /**
     * Obtiene el saldo de la cuenta principal del usuario
     * @param userId ID del usuario
     * @return Información de saldo de la cuenta
     */
    public SaldoResponse obtenerSaldo(Long userId) {
        log.info("Consultando saldo para usuario ID: {}", userId);
        
        Cuenta cuenta = cuentaRepository.findFirstByUsuarioIdOrderByFechaAperturaAsc(userId)
                .orElseThrow(() -> {
                    log.error("No se encontró cuenta para usuario ID: {}", userId);
                    return new RuntimeException("Cuenta no encontrada");
                });

        // Enmascarar número de cuenta (mostrar solo últimos 4 dígitos)
        String numeroEnmascarado = "****" + cuenta.getNumeroCuenta().substring(
                Math.max(0, cuenta.getNumeroCuenta().length() - 4)
        );

        log.info("Saldo consultado exitosamente - Cuenta: {}, Saldo: {}", 
                numeroEnmascarado, cuenta.getSaldo());

        return new SaldoResponse(
                numeroEnmascarado,
                cuenta.getSaldo(),
                "COP",
                cuenta.getTipo()
        );
    }
}
