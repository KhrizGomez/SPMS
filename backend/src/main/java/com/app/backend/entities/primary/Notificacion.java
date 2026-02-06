package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'notificaciones' en la BD principal.
 */
@Data
@Entity
@Table(name = "notificaciones")
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notificacion")
    private Integer idNotificacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoNotificacion tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud")
    private Solicitud solicitud;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "mensaje", nullable = false, columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "canal", length = 50)
    private String canal;

    @Column(name = "esta_leida")
    private Boolean estaLeida = false;

    @Column(name = "fecha_lectura")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaLectura;

    @Column(name = "esta_enviada")
    private Boolean estaEnviada = false;

    @Column(name = "fecha_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio;

    @Column(name = "error_envio", columnDefinition = "TEXT")
    private String errorEnvio;

    @Column(name = "programada_para")
    @Temporal(TemporalType.TIMESTAMP)
    private Date programadaPara;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

