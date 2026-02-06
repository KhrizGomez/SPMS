package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.List;

/**
 * Entidad que representa la tabla 'semestres' en la BD secundaria (SGA).
 * Representa los períodos académicos.
 */
@Data
@Entity
@Table(name = "semestres")
public class Semestre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_semestre")
    private Integer idSemestre;

    @Column(name = "codigo_periodo", nullable = false, unique = true, length = 50)
    private String codigoPeriodo;

    @Column(name = "nombre_periodo", length = 100)
    private String nombrePeriodo;

    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    @Column(name = "fecha_fin", nullable = false)
    private Date fechaFin;

    @Column(name = "fecha_inicio_matriculas")
    private Date fechaInicioMatriculas;

    @Column(name = "fecha_fin_matriculas")
    private Date fechaFinMatriculas;

    @Column(name = "estado_activo")
    private Boolean estadoActivo;

    @Column(name = "es_periodo_actual")
    private Boolean esPeriodoActual;

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

    // Relaciones
    @OneToMany(mappedBy = "semestreActual", cascade = CascadeType.ALL)
    private List<InfoEstudiante> estudiantes;
}
