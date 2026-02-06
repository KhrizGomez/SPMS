package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Facultad del sistema principal.
 */
public interface IFacultadRepository extends JpaRepository<Facultad, Integer> {
    Optional<Facultad> findByNombreFacultad(String nombreFacultad);
    List<Facultad> findByUniversidad_IdUniversidad(Integer idUniversidad);
}

