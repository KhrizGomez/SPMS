package com.app.backend.dtos.secondary;

import lombok.Data;
import java.util.Date;

@Data
public class InfoDeanDTO {
    private Integer IdUsuario;
    private Integer IdFacultad;
    private Date FechaNombramiento;
    private Boolean Estado;
}
