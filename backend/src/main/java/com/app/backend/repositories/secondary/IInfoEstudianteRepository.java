package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.InfoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para InfoEstudiante del sistema secundario (SGA).
 */
public interface IInfoEstudianteRepository extends JpaRepository<InfoEstudiante, Integer> {
    Optional<InfoEstudiante> findByUsuario_IdUsuario(Integer idUsuario);
    Optional<InfoEstudiante> findByUsuario_Cedula(String cedula);
    Optional<InfoEstudiante> findByNumeroMatricula(String numeroMatricula);
    List<InfoEstudiante> findByCarrera_IdCarrera(Integer idCarrera);
    List<InfoEstudiante> findBySemestreActual_IdSemestre(Integer idSemestre);
    List<InfoEstudiante> findByMatriculadoTrue();
    List<InfoEstudiante> findByEstadoAcademico(String estadoAcademico);
}
