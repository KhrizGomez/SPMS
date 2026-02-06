package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Semestre del sistema principal.
 */
public interface ISemestreRepository extends JpaRepository<Semestre, Integer> {
    Optional<Semestre> findByCodigoPeriodo(String codigoPeriodo);
    List<Semestre> findByEstaActivoTrue();
}

