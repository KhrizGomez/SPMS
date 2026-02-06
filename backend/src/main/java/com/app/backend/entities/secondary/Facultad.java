package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.List;

/**
 * Entidad que representa la tabla 'facultades' en la BD secundaria (SGA).
 */
@Data
@Entity
@Table(name = "facultades")
public class Facultad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_facultad")
    private Integer idFacultad;

    @Column(name = "nombre_facultad", nullable = false, unique = true, length = 255)
    private String nombreFacultad;

    @Column(name = "codigo_facultad", unique = true, length = 20)
    private String codigoFacultad;

    @Column(name = "ubicacion_oficina", length = 255)
    private String ubicacionOficina;

    @Column(name = "telefono_oficina", length = 20)
    private String telefonoOficina;

    @Column(name = "email_facultad", length = 255)
    private String emailFacultad;

    @Column(name = "fecha_creacion")
    private Date fechaCreacion;

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
    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL)
    private List<Carrera> carreras;

    @OneToMany(mappedBy = "facultad", cascade = CascadeType.ALL)
    private List<InfoDecano> decanos;
}
