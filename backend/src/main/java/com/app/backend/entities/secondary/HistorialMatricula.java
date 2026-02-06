package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'historial_matriculas' en la BD secundaria (SGA).
 * Registra el historial de matrículas de los estudiantes por período.
 */
@Data
@Entity
@Table(name = "historial_matriculas", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_estudiante", "id_semestre"})
})
public class HistorialMatricula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estudiante", nullable = false)
    private InfoEstudiante estudiante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre", nullable = false)
    private Semestre semestre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column(name = "paralelo", length = 1)
    private String paralelo;

    @Column(name = "fecha_matricula")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaMatricula;

    @Column(name = "tipo_matricula", length = 50)
    private String tipoMatricula;

    @Column(name = "estado_matricula", length = 50)
    private String estadoMatricula;

    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

