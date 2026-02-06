package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

/**
 * Entidad que representa la tabla 'estudiantes' en la BD principal.
 */
@Data
@Entity
@Table(name = "estudiantes")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estudiante")
    private Integer idEstudiante;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera", nullable = false)
    private Carrera carrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre")
    private Semestre semestre;

    @Column(name = "paralelo", length = 1)
    private String paralelo;

    @Column(name = "estado_academico", length = 50)
    private String estadoAcademico = "Regular";

    @Column(name = "fecha_matricula")
    private Date fechaMatricula;

    @Column(name = "es_externo")
    private Boolean esExterno = false;

    @Column(name = "id_estudiante_sga")
    private Integer idEstudianteSga;

    @Column(name = "ultima_sincronizacion")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date ultimaSincronizacion;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date actualizadoEn;
}

