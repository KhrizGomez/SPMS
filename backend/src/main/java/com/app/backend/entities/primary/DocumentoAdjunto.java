package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'documentos_adjuntos' en la BD principal.
 */
@Data
@Entity
@Table(name = "documentos_adjuntos")
public class DocumentoAdjunto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_documento")
    private Integer idDocumento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitud", nullable = false)
    private Solicitud solicitud;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_requisito")
    private RequisitoTramite requisito;

    @Column(name = "nombre_archivo", nullable = false)
    private String nombreArchivo;

    @Column(name = "nombre_original")
    private String nombreOriginal;

    @Column(name = "ruta_archivo", nullable = false, length = 500)
    private String rutaArchivo;

    @Column(name = "tamano_bytes")
    private Long tamanoBytes;

    @Column(name = "tipo_mime", length = 100)
    private String tipoMime;

    @Column(name = "checksum", length = 64)
    private String checksum;

    @Column(name = "es_valido")
    private Boolean esValido;

    @Column(name = "mensaje_validacion", columnDefinition = "TEXT")
    private String mensajeValidacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subido_por")
    private Usuario subidoPor;

    @Column(name = "fecha_subida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSubida;
}

