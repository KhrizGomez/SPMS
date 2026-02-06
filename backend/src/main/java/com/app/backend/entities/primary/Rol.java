package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * Entidad que representa la tabla 'roles' en la BD principal.
 */
@Data
@Entity
@Table(name = "roles")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Integer idRol;

    @Column(name = "nombre_rol", nullable = false, unique = true, length = 100)
    private String nombreRol;

    @Column(name = "descripcion_rol", columnDefinition = "TEXT")
    private String descripcionRol;

    @Column(name = "es_rol_sistema")
    private Boolean esRolSistema = false;

    @Column(name = "nivel_jerarquico")
    private Integer nivelJerarquico = 0;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<RolPermiso> rolesPermisos;

    @OneToMany(mappedBy = "rol", fetch = FetchType.LAZY)
    private List<UsuarioRol> usuariosRoles;
}

