package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para Usuario del sistema primario.
 */
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByCedula(String cedula);
    Optional<Usuario> findByCorreoInstitucional(String correoInstitucional);
}
