package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer IdUsuario;

    @Column(name = "cedula_identidad", nullable = false, unique = true)
    private String CedulaIdentidad;

    @Column(name = "nombre_usuario", nullable = false)
    private String NombreUsuario;

    @Column(name = "apellido_usuario", nullable = false)
    private String ApellidoUsuario;

    @Column(name = "correo_personal", nullable = false, unique = true)
    private String CorreoPersonal;

    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String CorreoInstitucional;

    @Column(name = "telefono")
    private String Telefono;

    @Column(name = "rol")
    private String Rol;

    @Column(name = "fecha_registro")
    private Date FechaRegistro;

//    @OneToOne(mappedBy = "FKDeanUser", cascade = CascadeType.ALL)
//    private InfoDean FKInfoDean;

    @OneToOne(mappedBy = "CoordinatorUser", cascade = CascadeType.ALL)
    private InfoCoordinator FKInfoCoordinator;
}
