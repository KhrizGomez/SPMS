package com.app.backend.dtos.secondary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para HistorialMatricula del sistema secundario (SGA).
 */
@Data
public class HistorialMatriculaDTO {
    private Integer idHistorial;
    private Integer idEstudiante;
    private String nombreEstudiante;
    private String numeroMatricula;
    private Integer idSemestre;
    private String codigoPeriodo;
    private Integer idCarrera;
    private String nombreCarrera;
    private String paralelo;
    private Date fechaMatricula;
    private String tipoMatricula;
    private String estadoMatricula;
    private String observaciones;
    private Date creadoEn;
}

