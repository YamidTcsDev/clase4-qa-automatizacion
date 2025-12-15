package com.banco.demo.repository;

import com.banco.demo.model.SolicitudPrestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio para operaciones de base de datos de SolicitudPrestamo
 */
@Repository
public interface SolicitudPrestamoRepository extends JpaRepository<SolicitudPrestamo, Long> {
    
    List<SolicitudPrestamo> findByUsuarioIdOrderByFechaSolicitudDesc(Long usuarioId);
}
