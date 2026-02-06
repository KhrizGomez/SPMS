package com.app.backend.services.secondary;import com.app.backend.dtos.secondary.InfoEstudianteDTO;
import java.util.List;

public interface IInfoEstudianteService {
    List<InfoEstudianteDTO> obtenerTodosLosInfoEstudiantes();
    InfoEstudianteDTO guardarInfoEstudiante(InfoEstudianteDTO infoEstudianteDTO);
}
