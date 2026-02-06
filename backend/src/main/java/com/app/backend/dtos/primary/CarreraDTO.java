package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Carrera del sistema principal.
 */
@Data
public class CarreraDTO {
    private Integer idCarrera;
    private String nombreCarrera;
    private String codigoCarrera;
    private Integer idFacultad;
    private String nombreFacultad;
    private Date creadoEn;
    private Date actualizadoEn;
}

