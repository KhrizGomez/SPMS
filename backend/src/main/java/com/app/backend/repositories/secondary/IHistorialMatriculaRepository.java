package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.HistorialMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio para HistorialMatricula del sistema secundario (SGA).
 */
public interface IHistorialMatriculaRepository extends JpaRepository<HistorialMatricula, Integer> {
    List<HistorialMatricula> findByEstudiante_IdEstudiante(Integer idEstudiante);
    List<HistorialMatricula> findBySemestre_IdSemestre(Integer idSemestre);
    List<HistorialMatricula> findByCarrera_IdCarrera(Integer idCarrera);
    List<HistorialMatricula> findByEstadoMatricula(String estadoMatricula);
    List<HistorialMatricula> findByEstudiante_IdEstudianteAndSemestre_IdSemestre(Integer idEstudiante, Integer idSemestre);
}

