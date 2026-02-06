package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Carrera del sistema principal.
 */
public interface ICarreraRepository extends JpaRepository<Carrera, Integer> {
    Optional<Carrera> findByCodigoCarrera(String codigoCarrera);
    List<Carrera> findByFacultad_IdFacultad(Integer idFacultad);
}

