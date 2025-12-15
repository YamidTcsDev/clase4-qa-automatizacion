package com.banco.demo.repository;

import com.banco.demo.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para operaciones de base de datos de Cuenta
 */
@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    
    List<Cuenta> findByUsuarioId(Long usuarioId);
    
    Optional<Cuenta> findFirstByUsuarioIdOrderByFechaAperturaAsc(Long usuarioId);
}
