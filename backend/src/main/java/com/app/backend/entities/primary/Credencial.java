package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'credenciales' en la BD principal.
 */
@Data
@Entity
@Table(name = "credenciales")
public class Credencial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_credencial")
    private Integer idCredencial;

    @Column(name = "hash_contrasena", nullable = false, columnDefinition = "TEXT")
    private String hashContrasena;

    @Column(name = "salt_contrasena")
    private String saltContrasena;

    @Column(name = "ultimo_cambio_contrasena")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoCambioContrasena;

    @Column(name = "contrasena_expira_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contrasenaExpiraEn;

    @Column(name = "intentos_fallidos")
    private Integer intentosFallidos = 0;

    @Column(name = "bloqueado_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bloqueadoHasta;

    @Column(name = "doble_factor_habilitado")
    private Boolean dobleFactorHabilitado = false;

    @Column(name = "secreto_doble_factor")
    private String secretoDobleFactor;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;
}

