package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.HistorialMatriculaDTO;
import java.util.List;

/**
 * Interfaz de servicio para HistorialMatricula del sistema secundario (SGA).
 */
public interface IHistorialMatriculaService {
    List<HistorialMatriculaDTO> obtenerTodosLosHistoriales();
    List<HistorialMatriculaDTO> obtenerPorEstudiante(Integer idEstudiante);
    List<HistorialMatriculaDTO> obtenerPorSemestre(Integer idSemestre);
    List<HistorialMatriculaDTO> obtenerPorCarrera(Integer idCarrera);
    HistorialMatriculaDTO guardarHistorial(HistorialMatriculaDTO dto);
}

