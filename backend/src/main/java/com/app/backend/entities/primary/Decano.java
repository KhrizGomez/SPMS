package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'decanos' en la BD principal.
 */
@Data
@Entity
@Table(name = "decanos")
public class Decano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_decano")
    private Integer idDecano;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;

    @Column(name = "fecha_nombramiento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaNombramiento;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    @Column(name = "id_decano_sga")
    private Integer idDecanoSga;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;
}

