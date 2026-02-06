package com.app.backend.dtos.primary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Credenciales del sistema primario.
 */
@Data
public class CredencialesDTO {
    private Integer idCredenciales;
    private String contrasena;
    private Date fechaModificacion;
    private Integer idUsuario;
}
