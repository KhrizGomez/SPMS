package com.app.backend.dtos;
import lombok.Data;
import java.util.Date;

@Data
public class UserDTO {
    private Integer IdUsuario;
    private String CedulaIdentidad;
    private String NombreUsuario;
    private String ApellidoUsuario;
    private String CorreoPersonal;
    private String CorreoInstitucional;
    private String Telefono;
    private String Rol;
    private Date FechaRegistro;
}
