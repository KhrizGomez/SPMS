package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad que representa la tabla 'usuarios_roles' en la BD principal.
 */
@Data
@Entity
@Table(name = "usuarios_roles")
@IdClass(UsuarioRolId.class)
public class UsuarioRol {
    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Id
    @Column(name = "id_rol")
    private Integer idRol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private Rol rol;

    @Column(name = "asignado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date asignadoEn;

    @Column(name = "asignado_por")
    private Integer asignadoPor;

    @Column(name = "es_principal")
    private Boolean esPrincipal = false;

    @Column(name = "valido_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validoDesde;

    @Column(name = "valido_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date validoHasta;
}

/**
 * Clase para la clave compuesta de UsuarioRol.
 */
@Data
class UsuarioRolId implements Serializable {
    private Integer idUsuario;
    private Integer idRol;
}

