package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.TipoEventoAuditoriaDTO;
import java.util.List;

/**
 * Interfaz de servicio para TipoEventoAuditoria del sistema secundario (SGA).
 */
public interface ITipoEventoAuditoriaService {
    List<TipoEventoAuditoriaDTO> obtenerTodosLosTipos();
    List<TipoEventoAuditoriaDTO> obtenerTiposActivos();
    TipoEventoAuditoriaDTO obtenerPorCodigo(String codigoEvento);
    TipoEventoAuditoriaDTO guardarTipo(TipoEventoAuditoriaDTO dto);
}

