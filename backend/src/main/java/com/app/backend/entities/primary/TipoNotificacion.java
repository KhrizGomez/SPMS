package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa la tabla 'tipos_notificacion' en la BD principal.
 */
@Data
@Entity
@Table(name = "tipos_notificacion")
public class TipoNotificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo")
    private Integer idTipo;

    @Column(name = "codigo_tipo", nullable = false, unique = true, length = 50)
    private String codigoTipo;

    @Column(name = "nombre_tipo", nullable = false, length = 100)
    private String nombreTipo;

    @Column(name = "plantilla_defecto", columnDefinition = "TEXT")
    private String plantillaDefecto;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;
}

