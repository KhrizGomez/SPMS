package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Carrera del sistema secundario (SGA).
 */
public interface ICarreraRepository extends JpaRepository<Carrera, Integer> {
    Optional<Carrera> findByCodigoCarrera(String codigoCarrera);
    List<Carrera> findByFacultad_IdFacultad(Integer idFacultad);
    List<Carrera> findByEstadoTrue();
}
