package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * Entidad que representa la tabla 'usuarios' en la BD principal.
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

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "correo_institucional", nullable = false, unique = true)
    private String correoInstitucional;

    @Column(name = "correo_personal", unique = true)
    private String correoPersonal;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    @Column(name = "correo_verificado")
    private Boolean correoVerificado = false;

    @Column(name = "telefono_verificado")
    private Boolean telefonoVerificado = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_configuracion")
    private ConfiguracionUsuario configuracionUsuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_credencial")
    private Credencial credencial;

    @Column(name = "id_usuario_sga")
    private Integer idUsuarioSga;

    @Column(name = "importado_de_sga")
    private Boolean importadoDeSga = false;

    @Column(name = "fecha_importacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaImportacion;

    @Column(name = "ultimo_acceso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoAcceso;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;

    @Column(name = "eliminado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eliminadoEn;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuariosRoles;
}
