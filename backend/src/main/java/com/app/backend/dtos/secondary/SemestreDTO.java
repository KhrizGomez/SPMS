package com.app.backend.dtos.secondary;

import java.sql.Date;
import lombok.Data;

/**
 * DTO para Semestre/Período académico del sistema secundario (SGA).
 */
@Data
public class SemestreDTO {
    private Integer idSemestre;
    private String codigoPeriodo;
    private String nombrePeriodo;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaInicioMatriculas;
    private Date fechaFinMatriculas;
    private Boolean estadoActivo;
    private Boolean esPeriodoActual;
}
