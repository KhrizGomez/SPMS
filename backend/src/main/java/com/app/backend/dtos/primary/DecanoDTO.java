package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Decano del sistema principal.
 */
@Data
public class DecanoDTO {
    private Integer idDecano;
    private Integer idUsuario;
    private String nombresUsuario;
    private String apellidosUsuario;
    private Integer idFacultad;
    private String nombreFacultad;
    private Date fechaNombramiento;
    private Boolean estaActivo;
}

