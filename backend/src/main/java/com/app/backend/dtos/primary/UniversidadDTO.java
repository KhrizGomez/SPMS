package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Universidad del sistema principal.
 */
@Data
public class UniversidadDTO {
    private Integer idUniversidad;
    private String nombreUniversidad;
    private Boolean esPublica;
    private Date creadoEn;
    private Date actualizadoEn;
}

