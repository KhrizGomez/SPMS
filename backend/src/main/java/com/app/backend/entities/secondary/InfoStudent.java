package com.app.backend.entities.secondary;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "info_estudiantes")
public class InfoStudent {
    @Id
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private User StudentUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Career FKInfoCarreraStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre")
    private Semester FKInfoSemesterStudent;

    @Column(name = "fecha_ingreso")
    private Date FechaIngreso;

    @Column(name = "estado_academico")
    private String EstadoAcademico;
}
