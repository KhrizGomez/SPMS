package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Rol del sistema principal.
 */
@Data
public class RolDTO {
    private Integer idRol;
    private String nombreRol;
    private String descripcionRol;
    private Boolean esRolSistema;
    private Integer nivelJerarquico;
    private Date creadoEn;
    private Date actualizadoEn;
}

