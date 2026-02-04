package com.app.backend.dtos;

import com.app.backend.entities.secondary.Career;
import lombok.Data;

@Data
public class InfoCoordinatorDTO {
    private Integer IdUsuario;

    private Career FKInfoCarreraCoordinator;
    private String OficinaAtencion;
    private Boolean Estado;
}
