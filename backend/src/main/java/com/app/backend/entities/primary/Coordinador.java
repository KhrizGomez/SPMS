package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;

/**
 * Entidad que representa la tabla 'coordinadores' en la BD principal.
 */
@Data
@Entity
@Table(name = "coordinadores")
public class Coordinador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_coordinador")
    private Integer idCoordinador;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false, unique = true)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @Column(name = "ubicacion_oficina")
    private String ubicacionOficina;

    @Column(name = "horario_atencion")
    private String horarioAtencion;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    @Column(name = "fecha_nombramiento")
    private Date fechaNombramiento;

    @Column(name = "id_coordinador_sga")
    private Integer idCoordinadorSga;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date actualizadoEn;
}

