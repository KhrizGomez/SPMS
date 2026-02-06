package com.app.backend.dtos.primary;

import lombok.Data;
import java.math.BigDecimal;
import java.util.Date;

/**
 * DTO para TipoTramite del sistema principal.
 */
@Data
public class TipoTramiteDTO {
    private Integer idTipoTramite;
    private String nombreTramite;
    private String descripcionTramite;
    private Integer idCategoria;
    private String nombreCategoria;
    private Integer idFlujo;
    private String nombreFlujo;
    private Integer diasEstimados;
    private Boolean requierePago;
    private BigDecimal montoPago;
    private Boolean estaActivo;
    private Boolean disponibleExternos;
    private Date creadoEn;
    private Date actualizadoEn;
}

