package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'log_sincronizacion' en la BD secundaria (SGA).
 * Registra las sincronizaciones con sistemas externos.
 */
@Data
@Entity
@Table(name = "log_sincronizacion")
public class LogSincronizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sync")
    private Integer idSync;

    @Column(name = "sistema_destino", nullable = false, length = 100)
    private String sistemaDestino;

    @Column(name = "tipo_entidad", nullable = false, length = 50)
    private String tipoEntidad;

    @Column(name = "id_entidad_local", nullable = false)
    private Integer idEntidadLocal;

    @Column(name = "id_entidad_externa")
    private Integer idEntidadExterna;

    @Column(name = "accion", nullable = false, length = 20)
    private String accion;

    @Column(name = "estado", length = 20)
    private String estado;

    @Column(name = "datos_enviados", columnDefinition = "jsonb")
    private String datosEnviados;

    @Column(name = "respuesta_recibida", columnDefinition = "jsonb")
    private String respuestaRecibida;

    @Column(name = "mensaje_error", columnDefinition = "TEXT")
    private String mensajeError;

    @Column(name = "intentos")
    private Integer intentos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitado_por")
    private Usuario solicitadoPor;

    @Column(name = "fecha_solicitud")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSolicitud;

    @Column(name = "fecha_procesado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaProcesado;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

