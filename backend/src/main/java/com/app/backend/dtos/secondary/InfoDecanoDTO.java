package com.app.backend.dtos.secondary;

import lombok.Data;
import java.sql.Date;

/**
 * DTO para InfoDecano del sistema secundario (SGA).
 */
@Data
public class InfoDecanoDTO {
    private Integer idDecano;
    private Integer idUsuario;
    private Integer idFacultad;
    private java.util.Date fechaNombramiento;
    private Date fechaFinPeriodo;
    private String resolucionNombramiento;
    private Boolean estado;
    private String observaciones;

    // Datos adicionales para la vista
    private String nombreFacultad;
    private String codigoFacultad;
}
