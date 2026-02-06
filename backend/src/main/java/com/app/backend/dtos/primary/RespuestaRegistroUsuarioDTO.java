package com.app.backend.dtos.primary;

import lombok.Data;

/**
 * DTO para la respuesta del registro de usuario.
 * Contiene información del usuario creado y las credenciales generadas.
 */
@Data
public class RespuestaRegistroUsuarioDTO {
    // Información del usuario
    private Integer idUsuario;
    private String nombres;
    private String apellidos;
    private String cedula;
    private String correoInstitucional;
    private String correoPersonal;
    private String rol;

    // Credenciales generadas (solo se envían una vez)
    private String usuarioGenerado;
    private String contrasenaGenerada;

    // Estado del registro
    private boolean exitoso;
    private String mensaje;
}
