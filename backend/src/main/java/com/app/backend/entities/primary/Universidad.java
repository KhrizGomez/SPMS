package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * Entidad que representa la tabla 'universidades' en la BD principal.
 */
@Data
@Entity
@Table(name = "universidades")
public class Universidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_universidad")
    private Integer idUniversidad;

    @Column(name = "nombre_universidad", nullable = false)
    private String nombreUniversidad;

    @Column(name = "es_publica", nullable = false)
    private Boolean esPublica = true;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;

    @Column(name = "eliminado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date eliminadoEn;

    @OneToMany(mappedBy = "universidad", fetch = FetchType.LAZY)
    private List<Facultad> facultades;
}

