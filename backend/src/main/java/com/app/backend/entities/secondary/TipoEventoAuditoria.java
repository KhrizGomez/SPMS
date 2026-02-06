package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidad que representa la tabla 'tipos_evento_auditoria' en la BD secundaria (SGA).
 * Define los tipos de eventos que se pueden auditar en el sistema.
 */
@Data
@Entity
@Table(name = "tipos_evento_auditoria")
public class TipoEventoAuditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_evento")
    private Integer idTipoEvento;

    @Column(name = "codigo_evento", nullable = false, unique = true, length = 50)
    private String codigoEvento;

    @Column(name = "nombre_evento", nullable = false, length = 100)
    private String nombreEvento;

    @Column(name = "descripcion_evento", columnDefinition = "TEXT")
    private String descripcionEvento;

    @Column(name = "severidad", length = 20)
    private String severidad;

    @Column(name = "esta_activo")
    private Boolean estaActivo;
}

