package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.AuditoriaDTO;
import java.util.Date;
import java.util.List;

/**
 * Interfaz de servicio para Auditoria del sistema secundario (SGA).
 */
public interface IAuditoriaService {
    List<AuditoriaDTO> obtenerTodasLasAuditorias();
    List<AuditoriaDTO> obtenerPorUsuario(Integer idUsuario);
    List<AuditoriaDTO> obtenerPorTipoEvento(Integer idTipoEvento);
    List<AuditoriaDTO> obtenerPorTabla(String tablaAfectada);
    List<AuditoriaDTO> obtenerPorRangoFechas(Date fechaInicio, Date fechaFin);
    AuditoriaDTO guardarAuditoria(AuditoriaDTO dto);
}

