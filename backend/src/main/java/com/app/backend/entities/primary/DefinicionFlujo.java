package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'definiciones_flujo' en la BD principal.
 */
@Data
@Entity
@Table(name = "definiciones_flujo")
public class DefinicionFlujo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_flujo")
    private Integer idFlujo;

    @Column(name = "nombre_flujo", nullable = false)
    private String nombreFlujo;

    @Column(name = "descripcion_flujo", columnDefinition = "TEXT")
    private String descripcionFlujo;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    @Column(name = "version")
    private Integer version = 1;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Usuario creadoPor;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;
}

