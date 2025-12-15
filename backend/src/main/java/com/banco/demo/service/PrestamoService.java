package com.banco.demo.service;

import com.banco.demo.dto.*;
import com.banco.demo.model.SolicitudPrestamo;
import com.banco.demo.repository.SolicitudPrestamoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Servicio de gestión de préstamos
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PrestamoService {

    private final SolicitudPrestamoRepository solicitudRepository;
    private static final Double TASA_INTERES_MENSUAL = 1.5; // 1.5% mensual

    /**
     * Calcula la cuota mensual de un préstamo
     * @param request Datos del préstamo (monto y plazo)
     * @return Cálculo de cuota mensual y total a pagar
     */
    public CalculoCuotaResponse calcularCuota(CalculoCuotaRequest request) {
        log.info("Calculando cuota para monto: {} y plazo: {} meses", 
                request.getMonto(), request.getPlazoMeses());

        BigDecimal monto = request.getMonto();
        Integer plazo = request.getPlazoMeses();
        
        // Convertir tasa de interés a decimal
        BigDecimal tasaDecimal = BigDecimal.valueOf(TASA_INTERES_MENSUAL / 100);
        
        // Fórmula de cuota: M * [i * (1 + i)^n] / [(1 + i)^n - 1]
        BigDecimal unoMasTasa = BigDecimal.ONE.add(tasaDecimal);
        BigDecimal potencia = unoMasTasa.pow(plazo);
        
        BigDecimal numerador = monto.multiply(tasaDecimal).multiply(potencia);
        BigDecimal denominador = potencia.subtract(BigDecimal.ONE);
        
        BigDecimal cuotaMensual = numerador.divide(denominador, 0, RoundingMode.HALF_UP);
        BigDecimal totalPagar = cuotaMensual.multiply(BigDecimal.valueOf(plazo));

        log.info("Cuota calculada: {} - Total a pagar: {}", cuotaMensual, totalPagar);

        return new CalculoCuotaResponse(cuotaMensual, TASA_INTERES_MENSUAL, totalPagar);
    }

    /**
     * Crea una nueva solicitud de préstamo
     * @param request Datos de la solicitud
     * @return Confirmación de solicitud creada
     */
    public PrestamoResponse solicitarPrestamo(PrestamoRequest request) {
        log.info("Creando solicitud de préstamo para usuario ID: {}", request.getUserId());

        // Calcular cuota mensual
        CalculoCuotaRequest calculoRequest = new CalculoCuotaRequest(
                request.getMonto(), 
                request.getPlazoMeses()
        );
        CalculoCuotaResponse calculo = calcularCuota(calculoRequest);

        // Generar número de solicitud
        String numeroSolicitud = generarNumeroSolicitud();

        // Crear entidad
        SolicitudPrestamo solicitud = new SolicitudPrestamo();
        solicitud.setUsuarioId(request.getUserId());
        solicitud.setMonto(request.getMonto());
        solicitud.setPlazoMeses(request.getPlazoMeses());
        solicitud.setProposito(request.getProposito());
        solicitud.setCuotaMensual(calculo.getCuotaMensual());
        solicitud.setEstado("EN_REVISION");
        solicitud.setFechaSolicitud(LocalDate.now());
        solicitud.setNumeroSolicitud(numeroSolicitud);

        solicitudRepository.save(solicitud);

        log.info("Solicitud creada exitosamente: {}", numeroSolicitud);

        return new PrestamoResponse(
                numeroSolicitud,
                "EN_REVISION",
                LocalDateTime.now()
        );
    }

    /**
     * Obtiene todas las solicitudes de préstamo de un usuario
     * @param userId ID del usuario
     * @return Lista de solicitudes
     */
    public List<SolicitudDto> obtenerSolicitudes(Long userId) {
        log.info("Consultando solicitudes para usuario ID: {}", userId);

        List<SolicitudPrestamo> solicitudes = solicitudRepository.findByUsuarioIdOrderByFechaSolicitudDesc(userId);

        log.info("Se encontraron {} solicitudes", solicitudes.size());

        return solicitudes.stream()
                .map(s -> new SolicitudDto(
                        s.getNumeroSolicitud(),
                        s.getMonto(),
                        s.getPlazoMeses(),
                        s.getCuotaMensual(),
                        s.getEstado(),
                        s.getFechaSolicitud()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Genera un número único de solicitud
     * @return Número de solicitud formato SOL-YYYYMMDD-XXXX
     */
    private String generarNumeroSolicitud() {
        String fecha = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        long count = solicitudRepository.count() + 1;
        String secuencia = String.format("%04d", count);
        return "SOL-" + fecha + "-" + secuencia;
    }
}
