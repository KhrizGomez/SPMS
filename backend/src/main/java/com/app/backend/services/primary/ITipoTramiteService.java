package com.app.backend.services.primary;

import com.app.backend.dtos.primary.TipoTramiteDTO;
import java.util.List;

/**
 * Interfaz del servicio para TipoTramite del sistema principal.
 */
public interface ITipoTramiteService {
    List<TipoTramiteDTO> obtenerTodosLosTiposTramite();
    List<TipoTramiteDTO> obtenerActivos();
    List<TipoTramiteDTO> obtenerPorCategoria(Integer idCategoria);
    TipoTramiteDTO obtenerPorId(Integer id);
    TipoTramiteDTO crearTipoTramite(TipoTramiteDTO dto);
    TipoTramiteDTO actualizarTipoTramite(Integer id, TipoTramiteDTO dto);
    void eliminarTipoTramite(Integer id);
}

