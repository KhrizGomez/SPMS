package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.InfoDecano;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio para InfoDecano del sistema secundario (SGA).
 */
public interface IInfoDecanoRepository extends JpaRepository<InfoDecano, Integer> {
    Optional<InfoDecano> findByUsuario_IdUsuario(Integer idUsuario);
    Optional<InfoDecano> findByFacultad_IdFacultad(Integer idFacultad);
    List<InfoDecano> findByEstadoTrue();
}
