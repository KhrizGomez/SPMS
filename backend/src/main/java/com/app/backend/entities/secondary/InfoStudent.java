package com.app.backend.entities.secondary;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "info_estudiantes")
public class InfoStudent {
    @Id
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id_usuario")
    private User FKStudentUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Career FKInfoCarreraStudent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_semestre_actual")
    private Semester FKInfoSemesterStudent;

    @Column(name = "fecha_ingreso")
    private Date FechaIngreso;

    @Column(name = "estado_academico")
    private String EstadoAcademico;
}
