package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Date;
import java.util.List;

/**
 * Entidad que representa la tabla 'solicitudes' en la BD principal.
 */
@Data
@Entity
@Table(name = "solicitudes")
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitud")
    private Integer idSolicitud;

    @Column(name = "codigo_solicitud", unique = true, length = 50)
    private String codigoSolicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", nullable = false)
    private TipoTramite tipoTramite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creado_por")
    private Usuario creadoPor;

    @Column(name = "detalles_solicitud", columnDefinition = "TEXT")
    private String detallesSolicitud;

    @Column(name = "prioridad", length = 20)
    private String prioridad = "normal";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paso_actual")
    private PasoFlujo pasoActual;

    @Column(name = "estado_actual", length = 50)
    private String estadoActual = "pendiente";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignado_a")
    private Usuario asignadoA;

    @Column(name = "fecha_estimada_fin")
    private Date fechaEstimadaFin;

    @Column(name = "fecha_real_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date fechaRealFin;

    @Column(name = "resolucion", columnDefinition = "TEXT")
    private String resolucion;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date actualizadoEn;

    @OneToMany(mappedBy = "solicitud", fetch = FetchType.LAZY)
    private List<DocumentoAdjunto> documentosAdjuntos;

    @OneToMany(mappedBy = "solicitud", fetch = FetchType.LAZY)
    private List<SeguimientoSolicitud> seguimientos;
}

