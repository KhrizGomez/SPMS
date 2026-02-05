package com.app.backend.entities.secondary;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;
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

    @OneToMany(mappedBy = "FKInfoSemesterStudent", cascade = CascadeType.ALL)
    private List<InfoStudent> FKInfoStudents;
}
