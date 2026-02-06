package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.CarreraDTO;
import java.util.List;

public interface ICarreraService {
    List<CarreraDTO> obtenerTodasLasCarreras();
    CarreraDTO guardarCarrera(CarreraDTO carreraDTO);
}
