package com.app.backend.dtos.primary;

import lombok.Data;
import java.sql.Date;

/**
 * DTO para Solicitud del sistema principal.
 */
@Data
public class SolicitudDTO {
    private Integer idSolicitud;
    private String codigoSolicitud;
    private Integer idTipoTramite;
    private String nombreTramite;
    private Integer idUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private Integer idCarrera;
    private String nombreCarrera;
    private Integer idFacultad;
    private String nombreFacultad;
    private String detallesSolicitud;
    private String prioridad;
    private Integer idPasoActual;
    private String estadoActual;
    private Integer idAsignadoA;
    private String nombresAsignadoA;
    private Date fechaEstimadaFin;
    private java.util.Date fechaRealFin;
    private String resolucion;
    private java.util.Date creadoEn;
    private java.util.Date actualizadoEn;
}

