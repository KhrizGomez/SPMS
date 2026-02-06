package com.app.backend.dtos.secondary;

import java.sql.Date;
import lombok.Data;

/**
 * DTO para Facultad del sistema secundario (SGA).
 */
@Data
public class FacultadDTO {
    private Integer idFacultad;
    private String nombreFacultad;
    private String codigoFacultad;
    private String ubicacionOficina;
    private String telefonoOficina;
    private String emailFacultad;
    private Date fechaCreacion;
    private Boolean estado;
}
