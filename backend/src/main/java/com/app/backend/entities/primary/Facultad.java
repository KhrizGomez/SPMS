package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * Entidad que representa la tabla 'facultades' en la BD principal.
 */
@Data
@Entity
@Table(name = "facultades")
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facultad")
    private Integer idFacultad;

    @Column(name = "nombre_facultad", nullable = false, unique = true)
    private String nombreFacultad;

    @Column(name = "ubicacion_oficina")
    private String ubicacionOficina;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_universidad", nullable = false)
    private Universidad universidad;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;

    @Column(name = "eliminado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eliminadoEn;

    @OneToMany(mappedBy = "facultad", fetch = FetchType.LAZY)
    private List<Carrera> carreras;
}

