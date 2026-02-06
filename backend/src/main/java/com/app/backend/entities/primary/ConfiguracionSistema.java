package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'configuraciones_sistema' en la BD principal.
 */
@Data
@Entity
@Table(name = "configuraciones_sistema")
public class ConfiguracionSistema {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_config")
    private Integer idConfig;

    @Column(name = "clave_config", nullable = false, unique = true)
    private String claveConfig;

    @Column(name = "valor_config", columnDefinition = "TEXT")
    private String valorConfig;

    @Column(name = "tipo_config", nullable = false, length = 50)
    private String tipoConfig = "texto";

    @Column(name = "categoria_config", length = 100)
    private String categoriaConfig;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "es_editable")
    private Boolean esEditable = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "editable_por_rol")
    private Rol editablePorRol;

    @Column(name = "regex_validacion", length = 500)
    private String regexValidacion;

    @Column(name = "valor_defecto", columnDefinition = "TEXT")
    private String valorDefecto;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "actualizado_por")
    private Usuario actualizadoPor;
}

