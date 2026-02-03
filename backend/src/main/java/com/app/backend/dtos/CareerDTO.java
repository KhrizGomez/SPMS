package com.app.backend.dtos;
import lombok.Data;

@Data
public class CareerDTO {
    private Integer IdCarrera;
    private String NombreCarrera;
    private String CodigoCarrera;

    private Integer IdFacultad;
    private String NombreFacultad;
}
