package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.InfoCoordinador;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para InfoCoordinador del sistema secundario (SGA).
 */
public interface IInfoCoordinadorRepository extends JpaRepository<InfoCoordinador, Integer> {
    Optional<InfoCoordinador> findByUsuario_IdUsuario(Integer idUsuario);
    Optional<InfoCoordinador> findByCarrera_IdCarrera(Integer idCarrera);
    List<InfoCoordinador> findByEstadoTrue();
}
