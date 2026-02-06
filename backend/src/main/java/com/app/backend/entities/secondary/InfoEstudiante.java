package com.app.backend.entities.secondary;


import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad que representa la tabla 'info_estudiantes' en la BD secundaria (SGA).
 * Contiene información específica de estudiantes.
 */
@Data
@Entity
@Table(name = "info_estudiantes")
public class InfoEstudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre_actual")
    private Semestre semestreActual;

    @Column(name = "numero_matricula", unique = true, length = 50)
    private String numeroMatricula;

    @Column(name = "paralelo", length = 1)
    private String paralelo;

    @Column(name = "jornada", length = 20)
    private String jornada;

    @Column(name = "fecha_ingreso")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaIngreso;

    @Column(name = "fecha_egreso")
    @Temporal(TemporalType.DATE)
    private Date fechaEgreso;

    @Column(name = "estado_academico", length = 50)
    private String estadoAcademico;

    @Column(name = "promedio_general", precision = 4, scale = 2)
    private BigDecimal promedioGeneral;

    @Column(name = "creditos_aprobados")
    private Integer creditosAprobados;

    @Column(name = "creditos_totales")
    private Integer creditosTotales;

    @Column(name = "matriculado")
    private Boolean matriculado;

    @Column(name = "es_becado")
    private Boolean esBecado;

    @Column(name = "tipo_beca", length = 100)
    private String tipoBeca;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

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
}
