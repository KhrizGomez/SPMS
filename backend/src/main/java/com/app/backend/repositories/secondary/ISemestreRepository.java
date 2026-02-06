package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Semestre del sistema secundario (SGA).
 */
public interface ISemestreRepository extends JpaRepository<Semestre, Integer> {
    Optional<Semestre> findByCodigoPeriodo(String codigoPeriodo);
    List<Semestre> findByEstadoActivoTrue();
    Optional<Semestre> findByEsPeriodoActualTrue();
}
