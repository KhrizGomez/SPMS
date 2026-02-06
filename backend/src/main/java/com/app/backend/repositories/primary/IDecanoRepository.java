package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Decano;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Decano del sistema principal.
 */
public interface IDecanoRepository extends JpaRepository<Decano, Integer> {
    Optional<Decano> findByUsuario_IdUsuario(Integer idUsuario);
    List<Decano> findByFacultad_IdFacultad(Integer idFacultad);
    List<Decano> findByEstaActivoTrue();
}

