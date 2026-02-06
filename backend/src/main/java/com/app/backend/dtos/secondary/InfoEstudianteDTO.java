package com.app.backend.dtos.secondary;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO para InfoEstudiante del sistema secundario (SGA).
 */
@Data
public class InfoEstudianteDTO {
    private Integer idEstudiante;
    private Integer idUsuario;
    private Integer idCarrera;
    private Integer idSemestreActual;
    private String numeroMatricula;
    private String paralelo;
    private String jornada;
    private Date fechaIngreso;
    private Date fechaEgreso;
    private String estadoAcademico;
    private BigDecimal promedioGeneral;
    private Integer creditosAprobados;
    private Integer creditosTotales;
    private Boolean matriculado;
    private Boolean esBecado;
    private String tipoBeca;
    private String observaciones;

    // Datos adicionales para la vista
    private String nombreCarrera;
    private String codigoCarrera;
    private String codigoPeriodo;
}
