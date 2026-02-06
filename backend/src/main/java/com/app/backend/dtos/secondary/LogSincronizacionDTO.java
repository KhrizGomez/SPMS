package com.app.backend.dtos.secondary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para LogSincronizacion del sistema secundario (SGA).
 */
@Data
public class LogSincronizacionDTO {
    private Integer idSync;
    private String sistemaDestino;
    private String tipoEntidad;
    private Integer idEntidadLocal;
    private Integer idEntidadExterna;
    private String accion;
    private String estado;
    private String datosEnviados;
    private String respuestaRecibida;
    private String mensajeError;
    private Integer intentos;
    private Integer idUsuarioSolicitante;
    private String nombreUsuarioSolicitante;
    private Date fechaSolicitud;
    private Date fechaProcesado;
    private Date creadoEn;
}

