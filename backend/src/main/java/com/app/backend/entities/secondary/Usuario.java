package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'usuarios' en la BD secundaria (SGA).
 * Base para todos los roles: estudiante, coordinador, decano, administrador, docente.
 */
@Data
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "cedula", nullable = false, unique = true, length = 20)
    private String cedula;

    @Column(name = "nombres", nullable = false, length = 255)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 255)
    private String apellidos;

    @Column(name = "correo_personal", nullable = false, unique = true, length = 255)
    private String correoPersonal;

    @Column(name = "correo_institucional", nullable = false, unique = true, length = 255)
    private String correoInstitucional;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @Column(name = "genero", length = 1)
    private String genero;

    @Column(name = "direccion", columnDefinition = "TEXT")
    private String direccion;

    @Column(name = "rol", nullable = false, length = 50)
    private String rol;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "fecha_registro")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;

    @Column(name = "ultimo_acceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "actualizado_por")
    private Integer actualizadoPor;

    // Relaciones
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private InfoEstudiante infoEstudiante;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private InfoCoordinador infoCoordinador;

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private InfoDecano infoDecano;
}
