package com.app.backend.dtos.primary;

import lombok.Data;
import java.sql.Date;

/**
 * DTO para Semestre del sistema principal.
 */
@Data
public class SemestreDTO {
    private Integer idSemestre;
    private String codigoPeriodo;
    private String nombrePeriodo;
    private Date fechaInicio;
    private Date fechaFin;
    private Boolean estaActivo;
}

