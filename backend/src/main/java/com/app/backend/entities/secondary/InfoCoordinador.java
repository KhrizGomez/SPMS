package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

/**
 * Entidad que representa la tabla 'info_coordinadores' en la BD secundaria (SGA).
 * Contiene información específica de coordinadores de carrera.
 */
@Data
@Entity
@Table(name = "info_coordinadores")
public class InfoCoordinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coordinador")
    private Integer idCoordinador;

    // Relación con Usuario
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    // Relación con Carrera
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column(name = "oficina_atencion", length = 255)
    private String oficinaAtencion;

    @Column(name = "horario_atencion", length = 255)
    private String horarioAtencion;

    @Column(name = "extension_telefonica", length = 20)
    private String extensionTelefonica;

    @Column(name = "fecha_nombramiento")
    private Date fechaNombramiento;

    @Column(name = "fecha_fin_periodo")
    private Date fechaFinPeriodo;

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