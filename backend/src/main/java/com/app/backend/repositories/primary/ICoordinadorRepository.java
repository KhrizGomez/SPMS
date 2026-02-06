package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Coordinador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Coordinador del sistema principal.
 */
public interface ICoordinadorRepository extends JpaRepository<Coordinador, Integer> {
    Optional<Coordinador> findByUsuario_IdUsuario(Integer idUsuario);
    List<Coordinador> findByCarrera_IdCarrera(Integer idCarrera);
    List<Coordinador> findByEstaActivoTrue();
}

