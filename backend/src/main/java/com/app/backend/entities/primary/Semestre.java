package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

/**
 * Entidad que representa la tabla 'semestres' en la BD principal.
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

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date actualizadoEn;
}

