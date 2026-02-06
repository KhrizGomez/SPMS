package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'pasos_flujo' en la BD principal.
 */
@Data
@Entity
@Table(name = "pasos_flujo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"id_flujo", "orden_paso"})
})
public class PasoFlujo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paso")
    private Integer idPaso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_flujo", nullable = false)
    private DefinicionFlujo flujo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etapa", nullable = false)
    private EtapaProcesamiento etapa;

    @Column(name = "orden_paso", nullable = false)
    private Integer ordenPaso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_requerido")
    private Rol rolRequerido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asignar_auto_a_rol")
    private Rol asignarAutoARol;

    @Column(name = "horas_sla")
    private Integer horasSla;

    @Column(name = "es_opcional")
    private Boolean esOpcional = false;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

