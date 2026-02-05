package com.app.backend.dtos.secondary;

import java.sql.Date;
import lombok.Data;

@Data
public class FacultyDTO {
    private Integer IdFacultad;
    private String NombreFacultad;
    private String UbicacionOficina;
    private Date FechaCreacion;
}
