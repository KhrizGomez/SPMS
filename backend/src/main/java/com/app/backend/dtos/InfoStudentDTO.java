package com.app.backend.dtos;

import com.app.backend.entities.secondary.Career;
import com.app.backend.entities.secondary.Semester;
import lombok.Data;

import java.util.Date;

@Data
public class InfoStudentDTO {
    private Integer IdUsuario;

    private Integer IdCarrera;
    private Integer IdSemestre;
    private Date FechaIngreso;
    private String EstadoAcademico;
}
