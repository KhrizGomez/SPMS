package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'seguimiento_solicitud' en la BD principal.
 */
@Data
@Entity
@Table(name = "seguimiento_solicitud")
public class SeguimientoSolicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_seguimiento")
    private Integer idSeguimiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_paso")
    private PasoFlujo paso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa")
    private EtapaProcesamiento etapa;

    @Column(name = "estado", nullable = false, length = 50)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "procesado_por")
    private Usuario procesadoPor;

    @Column(name = "comentarios", columnDefinition = "TEXT")
    private String comentarios;

    @Column(name = "fecha_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEntrada;

    @Column(name = "fecha_completado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompletado;

    @Column(name = "sla_excedido")
    private Boolean slaExcedido = false;
}

