package com.app.backend.dtos.secondary;

import lombok.Data;

/**
 * DTO para TipoEventoAuditoria del sistema secundario (SGA).
 */
@Data
public class TipoEventoAuditoriaDTO {
    private Integer idTipoEvento;
    private String codigoEvento;
    private String nombreEvento;
    private String descripcionEvento;
    private String severidad;
    private Boolean estaActivo;
}

