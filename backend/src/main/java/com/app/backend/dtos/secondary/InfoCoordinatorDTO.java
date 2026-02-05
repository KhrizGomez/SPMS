package com.app.backend.dtos.secondary;

import lombok.Data;

@Data
public class InfoCoordinatorDTO {
    private Integer IdUsuario;
    private Integer IdCarrera;
    private String OficinaAtencion;
    private Boolean Estado;
}
