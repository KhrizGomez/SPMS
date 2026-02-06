package com.app.backend.dtos.primary;

import lombok.Data;
import java.sql.Date;

/**
 * DTO para Coordinador del sistema principal.
 */
@Data
public class CoordinadorDTO {
    private Integer idCoordinador;
    private Integer idUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private Integer idCarrera;
    private String nombreCarrera;
    private String codigoCarrera;
    private String ubicacionOficina;
    private String horarioAtencion;
    private Boolean estaActivo;
    private Date fechaNombramiento;
}

