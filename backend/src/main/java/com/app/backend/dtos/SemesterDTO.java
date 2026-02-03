package com.app.backend.dtos;

import java.sql.Date;
import lombok.Data;


@Data
public class SemesterDTO {
    private Integer IdSemestre;
    private String CodigoPeriodo;
    private Date FechaInicio;
    private Date FechaFin;
    private Boolean EstadoActivo;
}
