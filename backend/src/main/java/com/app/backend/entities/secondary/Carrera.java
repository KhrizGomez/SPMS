package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * Entidad que representa la tabla 'carreras' en la BD secundaria (SGA).
 */
@Data
@Entity
@Table(name = "carreras")
public class Carrera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrera")
    private Integer idCarrera;

    @Column(name = "nombre_carrera", nullable = false, length = 255)
    private String nombreCarrera;

    @Column(name = "codigo_carrera", unique = true, length = 50)
    private String codigoCarrera;

    @Column(name = "duracion_semestres")
    private Integer duracionSemestres;

    @Column(name = "modalidad", length = 50)
    private String modalidad;

    @Column(name = "titulo_otorga", length = 255)
    private String tituloOtorga;

    @Column(name = "estado")
    private Boolean estado;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date actualizadoEn;

    @Column(name = "creado_por")
    private Integer creadoPor;

    @Column(name = "actualizado_por")
    private Integer actualizadoPor;

    // Relaciones
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<InfoCoordinador> coordinadores;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL)
    private List<InfoEstudiante> estudiantes;
}
