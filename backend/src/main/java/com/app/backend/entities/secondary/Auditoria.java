package com.app.backend.entities.secondary;

import jakarta.persistence.*;
import lombok.Data;
import java.net.InetAddress;
import java.util.Date;

/**
 * Entidad que representa la tabla 'auditoria' en la BD secundaria (SGA).
 * Registra todos los eventos de auditoría del sistema.
 */
@Data
@Entity
@Table(name = "auditoria")
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_auditoria")
    private Long idAuditoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_evento", nullable = false)
    private TipoEventoAuditoria tipoEvento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "tabla_afectada", length = 100)
    private String tablaAfectada;

    @Column(name = "id_registro_afectado")
    private Integer idRegistroAfectado;

    @Column(name = "valores_anteriores", columnDefinition = "jsonb")
    private String valoresAnteriores;

    @Column(name = "valores_nuevos", columnDefinition = "jsonb")
    private String valoresNuevos;

    @Column(name = "direccion_ip", length = 45)
    private String direccionIp;

    @Column(name = "agente_usuario", columnDefinition = "TEXT")
    private String agenteUsuario;

    @Column(name = "sistema_origen", length = 100)
    private String sistemaOrigen;

    @Column(name = "info_adicional", columnDefinition = "jsonb")
    private String infoAdicional;

    @Column(name = "fecha_evento")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEvento;

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;
}

