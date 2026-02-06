package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio para Rol del sistema principal.
 */
public interface IRolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByNombreRol(String nombreRol);
}

