package com.app.backend.dtos;

import com.app.backend.entities.secondary.Faculty;
import lombok.Data;

import java.util.Date;

@Data
public class InfoDeanDTO {
    private Integer IdUsuario;

    private Faculty FKInfoFacultadDean;
    private Date FechaNombramiento;
    private Boolean Estado;
}
