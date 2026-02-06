package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.LogSincronizacionDTO;
import java.util.List;

/**
 * Interfaz de servicio para LogSincronizacion del sistema secundario (SGA).
 */
public interface ILogSincronizacionService {
    List<LogSincronizacionDTO> obtenerTodosLosLogs();
    List<LogSincronizacionDTO> obtenerPorSistemaDestino(String sistemaDestino);
    List<LogSincronizacionDTO> obtenerPorEstado(String estado);
    List<LogSincronizacionDTO> obtenerPorTipoEntidad(String tipoEntidad);
    LogSincronizacionDTO guardarLog(LogSincronizacionDTO dto);
    LogSincronizacionDTO actualizarEstado(Integer idSync, String nuevoEstado);
}

