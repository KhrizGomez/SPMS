package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio para Universidad del sistema principal.
 */
public interface IUniversidadRepository extends JpaRepository<Universidad, Integer> {
    Optional<Universidad> findByNombreUniversidad(String nombreUniversidad);
}

