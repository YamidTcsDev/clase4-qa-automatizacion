package com.banco.demo.repository;

import com.banco.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para operaciones de base de datos de Usuario
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    Optional<Usuario> findByEmail(String email);
    
    Optional<Usuario> findByEmailAndPassword(String email, String password);
}
