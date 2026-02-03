package com.app.backend.entities.secondary;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "semestres")
public class Semester {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_semestre")
    private Integer IdSemestre;

    @Column(name = "codigo_periodo")
    private String CodigoPeriodo;

    @Column(name = "fecha_inicio")
    private Date FechaInicio;

    @Column(name = "fecha_fin")
    private Date FechaFin;

    @Column(name = "estado_activo")
    private Boolean EstadoActivo;
}
