package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * Entidad que representa la tabla 'roles_permisos' en la BD principal.
 */
@Data
@Entity
@Table(name = "roles_permisos")
@IdClass(RolPermisoId.class)
public class RolPermiso {
    @Id
    @Column(name = "id_rol")
    private Integer idRol;

    @Id
    @Column(name = "id_permiso")
    private Integer idPermiso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", insertable = false, updatable = false)
    private Rol rol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_permiso", insertable = false, updatable = false)
    private Permiso permiso;

    @Column(name = "otorgado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date otorgadoEn;

    @Column(name = "otorgado_por")
    private Integer otorgadoPor;
}

/**
 * Clase para la clave compuesta de RolPermiso.
 */
@Data
class RolPermisoId implements Serializable {
    private Integer idRol;
    private Integer idPermiso;
}

