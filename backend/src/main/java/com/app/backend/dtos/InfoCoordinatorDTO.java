package com.app.backend.dtos;

import com.app.backend.entities.secondary.Career;
import lombok.Data;

@Data
public class InfoCoordinatorDTO {
    private Integer IdUsuario;

    private Integer IdCarrera;
    private String OficinaAtencion;
    private Boolean Estado;
}
