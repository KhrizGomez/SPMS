package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Facultad del sistema principal.
 */
@Data
public class FacultadDTO {
    private Integer idFacultad;
    private String nombreFacultad;
    private String ubicacionOficina;
    private Integer idUniversidad;
    private String nombreUniversidad;
    private Date creadoEn;
    private Date actualizadoEn;
}

