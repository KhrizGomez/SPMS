package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'categorias_tramite' en la BD principal.
 */
@Data
@Entity
@Table(name = "categorias_tramite")
public class CategoriaTramite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nombre_categoria", nullable = false)
    private String nombreCategoria;

    @Column(name = "descripcion_categoria", columnDefinition = "TEXT")
    private String descripcionCategoria;

    @Column(name = "esta_activo")
    private Boolean estaActivo = true;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

