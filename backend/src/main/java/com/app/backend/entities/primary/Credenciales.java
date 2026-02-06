package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'credenciales' en la BD primaria.
 */
@Data
@Entity
@Table(name = "credenciales")
public class Credenciales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credenciales")
    private Integer idCredenciales;

    @Column(name = "contrasena", columnDefinition = "TEXT")
    private String contrasena;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}

