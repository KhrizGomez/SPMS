package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "carreras")
public class Career {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Integer IdCarrera;

    @Column(name = "nombre_carrera")
    private String NombreCarrera;

    @Column(name = "codigo_carrera")
    private String CodigoCarrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad")
    private Faculty FKCarreraFacultad;

    @OneToMany(mappedBy = "FKInfoCarreraCoordinator", cascade = CascadeType.ALL)
    private List<InfoCoordinator> FKInfoCoordinators;

    @OneToMany(mappedBy = "FKInfoCarreraStudent", cascade = CascadeType.ALL)
    private List<InfoStudent> FKInfoStudents;

}
