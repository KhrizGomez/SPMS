package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'permisos' en la BD principal.
 */
@Data
@Entity
@Table(name = "permisos", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_recurso", "accion"})
})
public class Permiso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer idPermiso;

    @Column(name = "nombre_permiso", nullable = false, unique = true, length = 100)
    private String nombrePermiso;

    @Column(name = "descripcion_permiso", columnDefinition = "TEXT")
    private String descripcionPermiso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_recurso")
    private Recurso recurso;

    @Column(name = "accion", nullable = false, length = 50)
    private String accion;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

