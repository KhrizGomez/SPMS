package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'etapas_procesamiento' en la BD principal.
 */
@Data
@Entity
@Table(name = "etapas_procesamiento")
public class EtapaProcesamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etapa")
    private Integer idEtapa;

    @Column(name = "nombre_etapa", nullable = false)
    private String nombreEtapa;

    @Column(name = "descripcion_etapa", columnDefinition = "TEXT")
    private String descripcionEtapa;

    @Column(name = "codigo_etapa", unique = true, length = 50)
    private String codigoEtapa;

    @Column(name = "es_inicial")
    private Boolean esInicial = false;

    @Column(name = "es_final")
    private Boolean esFinal = false;

    @Column(name = "requiere_aprobacion")
    private Boolean requiereAprobacion = true;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

