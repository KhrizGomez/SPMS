package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Entidad que representa la tabla 'tipos_tramite' en la BD principal.
 */
@Data
@Entity
@Table(name = "tipos_tramite")
public class TipoTramite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_tramite")
    private Integer idTipoTramite;

    @Column(name = "nombre_tramite", nullable = false)
    private String nombreTramite;

    @Column(name = "descripcion_tramite", columnDefinition = "TEXT")
    private String descripcionTramite;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_categoria")
    private CategoriaTramite categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_flujo")
    private DefinicionFlujo flujo;

    @Column(name = "dias_estimados")
    private Integer diasEstimados = 5;

    @Column(name = "requiere_pago")
    private Boolean requierePago = false;

    @Column(name = "monto_pago", precision = 10, scale = 2)
    private BigDecimal montoPago;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    @Column(name = "disponible_externos")
    private Boolean disponibleExternos = false;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;
}

