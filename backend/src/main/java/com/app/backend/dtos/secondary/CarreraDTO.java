package com.app.backend.dtos.secondary;

import lombok.Data;

/**
 * DTO para Carrera del sistema secundario (SGA).
 */
@Data
public class CarreraDTO {
    private Integer idCarrera;
    private String nombreCarrera;
    private String codigoCarrera;
    private Integer idFacultad;
    private String nombreFacultad;
    private Integer duracionSemestres;
    private String modalidad;
    private String tituloOtorga;
    private Boolean estado;
}
