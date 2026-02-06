package com.app.backend.dtos.secondary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Usuario del sistema secundario (SGA).
 */
@Data
public class UsuarioSecundarioDTO {
    private Integer idUsuario;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String correoPersonal;
    private String correoInstitucional;
    private String telefono;
    private Date fechaNacimiento;
    private String genero;
    private String direccion;
    private String rol;
    private Boolean estado;
    private Date fechaRegistro;
}
