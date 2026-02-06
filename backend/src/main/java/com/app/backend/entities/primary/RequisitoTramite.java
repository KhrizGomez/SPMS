package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'requisitos_tramite' en la BD principal.
 */
@Data
@Entity
@Table(name = "requisitos_tramite")
public class RequisitoTramite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_requisito")
    private Integer idRequisito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_tramite", nullable = false)
    private TipoTramite tipoTramite;

    @Column(name = "nombre_requisito", nullable = false)
    private String nombreRequisito;

    @Column(name = "descripcion_requisito", columnDefinition = "TEXT")
    private String descripcionRequisito;

    @Column(name = "es_obligatorio")
    private Boolean esObligatorio = true;

    @Column(name = "tipo_documento", length = 100)
    private String tipoDocumento;

    @Column(name = "tamano_max_mb")
    private Integer tamanoMaxMb = 10;

    @Column(name = "extensiones_permitidas")
    private String extensionesPermitidas = "pdf,jpg,png";

    @Column(name = "requiere_2fa")
    private Boolean requiere2fa = false;

    @Column(name = "numero_orden")
    private Integer numeroOrden = 0;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

