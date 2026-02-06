package com.app.backend.services.primary;

import com.app.backend.dtos.primary.SolicitudDTO;
import java.util.List;

/**
 * Interfaz del servicio para Solicitud del sistema principal.
 */
public interface ISolicitudService {
    List<SolicitudDTO> obtenerTodasLasSolicitudes();
    SolicitudDTO obtenerPorId(Integer id);
    SolicitudDTO obtenerPorCodigo(String codigoSolicitud);
    List<SolicitudDTO> obtenerPorUsuario(Integer idUsuario);
    List<SolicitudDTO> obtenerPorEstado(String estado);
    List<SolicitudDTO> obtenerPorAsignado(Integer idUsuario);
    SolicitudDTO crearSolicitud(SolicitudDTO dto);
    SolicitudDTO actualizarSolicitud(Integer id, SolicitudDTO dto);
    SolicitudDTO cambiarEstado(Integer id, String nuevoEstado);
    void eliminarSolicitud(Integer id);
}

