package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Notificacion del sistema principal.
 */
@Data
public class NotificacionDTO {
    private Integer idNotificacion;
    private Integer idTipo;
    private String codigoTipo;
    private String nombreTipo;
    private Integer idUsuario;
    private String nombresUsuario;
    private Integer idSolicitud;
    private String codigoSolicitud;
    private String titulo;
    private String mensaje;
    private String canal;
    private Boolean estaLeida;
    private Date fechaLectura;
    private Boolean estaEnviada;
    private Date fechaEnvio;
    private Date creadoEn;
}

