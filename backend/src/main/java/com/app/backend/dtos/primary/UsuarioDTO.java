package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * DTO para Usuario del sistema principal.
 */
@Data
public class UsuarioDTO {
    private Integer idUsuario;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String correoInstitucional;
    private String correoPersonal;
    private String telefono;
    private Boolean estaActivo;
    private Boolean correoVerificado;
    private Boolean telefonoVerificado;
    private Integer idConfiguracion;
    private Integer idCredencial;
    private Integer idUsuarioSga;
    private Boolean importadoDeSga;
    private Date ultimoAcceso;
    private Date creadoEn;
    private Date actualizadoEn;
    private List<String> roles;
}
