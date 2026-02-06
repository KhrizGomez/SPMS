package com.app.backend.services.primary;

import com.app.backend.dtos.primary.EstudianteDTO;
import java.util.List;

/**
 * Interfaz del servicio para Estudiante del sistema principal.
 */
public interface IEstudianteService {
    List<EstudianteDTO> obtenerTodosLosEstudiantes();
    EstudianteDTO obtenerPorId(Integer id);
    EstudianteDTO obtenerPorIdUsuario(Integer idUsuario);
    List<EstudianteDTO> obtenerPorCarrera(Integer idCarrera);
    EstudianteDTO crearEstudiante(EstudianteDTO dto);
    EstudianteDTO actualizarEstudiante(Integer id, EstudianteDTO dto);
    void eliminarEstudiante(Integer id);
}

