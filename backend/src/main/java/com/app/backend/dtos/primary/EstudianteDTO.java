package com.app.backend.dtos.primary;

import lombok.Data;
import java.sql.Date;

/**
 * DTO para Estudiante del sistema principal.
 */
@Data
public class EstudianteDTO {
    private Integer idEstudiante;
    private Integer idUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private String cedulaUsuario;
    private Integer idCarrera;
    private String nombreCarrera;
    private String codigoCarrera;
    private Integer idSemestre;
    private String codigoPeriodo;
    private String paralelo;
    private String estadoAcademico;
    private Date fechaMatricula;
    private Boolean esExterno;
    private Integer idEstudianteSga;
}

