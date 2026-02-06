package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.Facultad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio para Facultad del sistema secundario (SGA).
 */
public interface IFacultadRepository extends JpaRepository<Facultad, Integer> {
    Optional<Facultad> findByCodigoFacultad(String codigoFacultad);
    Optional<Facultad> findByNombreFacultad(String nombreFacultad);
}
