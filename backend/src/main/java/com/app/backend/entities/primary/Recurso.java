package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'recursos' en la BD principal.
 */
@Data
@Entity
@Table(name = "recursos")
public class Recurso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Integer idRecurso;

    @Column(name = "nombre_recurso", nullable = false, unique = true, length = 100)
    private String nombreRecurso;

    @Column(name = "descripcion_recurso", columnDefinition = "TEXT")
    private String descripcionRecurso;

    @Column(name = "modulo", length = 100)
    private String modulo;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

