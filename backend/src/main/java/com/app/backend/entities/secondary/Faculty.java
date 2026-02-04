package com.app.backend.entities.secondary;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "facultades")
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facultad")
    private Integer IdFacultad;

    @Column(name = "nombre_facultad", nullable = false, unique = true)
    private String NombreFacultad;

    @Column(name = "ubicacion_oficina")
    private String UbicacionOficina;

    @Column(name = "fecha_creacion")
    private Date FechaCreacion; 

    @OneToMany(mappedBy = "FKCarreraFacultad", cascade = CascadeType.ALL)
    private List<Career> FKCarreras;

    @OneToMany(mappedBy = "FKInfoFacultadDean", cascade = CascadeType.ALL)
    private List<InfoDean> FKInfoDeans;
}
