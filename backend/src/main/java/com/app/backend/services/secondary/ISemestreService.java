package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.SemestreDTO;
import java.util.List;

public interface ISemestreService {
    List<SemestreDTO> obtenerTodosLosSemestres();
    SemestreDTO guardarSemestre(SemestreDTO semestreDTO);
}
