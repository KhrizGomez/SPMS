package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.InfoCoordinadorDTO;
import java.util.List;

public interface IInfoCoordinadorService {
    List<InfoCoordinadorDTO> obtenerTodosLosInfoCoordinadores();
    InfoCoordinadorDTO guardarInfoCoordinador(InfoCoordinadorDTO infoCoordinadorDTO);
}
