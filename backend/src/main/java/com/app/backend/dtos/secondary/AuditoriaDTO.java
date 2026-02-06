package com.app.backend.dtos.secondary;

import lombok.Data;
import java.util.Date;

/**
 * DTO para Auditoria del sistema secundario (SGA).
 */
@Data
public class AuditoriaDTO {
    private Long idAuditoria;
    private Integer idTipoEvento;
    private String codigoEvento;
    private String nombreEvento;
    private Integer idUsuario;
    private String nombreUsuario;
    private String tablaAfectada;
    private Integer idRegistroAfectado;
    private String valoresAnteriores;
    private String valoresNuevos;
    private String direccionIp;
    private String agenteUsuario;
    private String sistemaOrigen;
    private String infoAdicional;
    private Date fechaEvento;
    private Date creadoEn;
}

