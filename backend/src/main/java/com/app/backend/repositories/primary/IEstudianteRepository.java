package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Estudiante del sistema principal.
 */
public interface IEstudianteRepository extends JpaRepository<Estudiante, Integer> {
    Optional<Estudiante> findByUsuario_IdUsuario(Integer idUsuario);
    List<Estudiante> findByCarrera_IdCarrera(Integer idCarrera);
    List<Estudiante> findBySemestre_IdSemestre(Integer idSemestre);
    Optional<Estudiante> findByIdEstudianteSga(Integer idEstudianteSga);
}

