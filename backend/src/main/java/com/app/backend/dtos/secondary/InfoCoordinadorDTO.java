package com.app.backend.dtos.secondary;

import lombok.Data;
import java.sql.Date;

/**
 * DTO para InfoCoordinador del sistema secundario (SGA).
 */
@Data
public class InfoCoordinadorDTO {
    private Integer idCoordinador;
    private Integer idUsuario;
    private Integer idCarrera;
    private String oficinaAtencion;
    private String horarioAtencion;
    private String extensionTelefonica;
    private Date fechaNombramiento;
    private Date fechaFinPeriodo;
    private Boolean estado;
    private String observaciones;

    // Datos adicionales para la vista
    private String nombreCarrera;
    private String codigoCarrera;
}
