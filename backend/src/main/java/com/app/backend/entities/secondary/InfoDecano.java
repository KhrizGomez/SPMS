package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

/**
 * Entidad que representa la tabla 'info_decanos' en la BD secundaria (SGA).
 * Contiene información específica de decanos de facultad.
 */
@Data
@Entity
@Table(name = "info_decanos")
public class InfoDecano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_decano")
    private Integer idDecano;

    // Relación con Usuario
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    // Relación con Facultad
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;

    @Column(name = "fecha_nombramiento")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaNombramiento;

    @Column(name = "fecha_fin_periodo")
    private Date fechaFinPeriodo;

    @Column(name = "resolucion_nombramiento", length = 100)
    private String resolucionNombramiento;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date actualizadoEn;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "actualizado_por")
    private Integer actualizadoPor;
}
