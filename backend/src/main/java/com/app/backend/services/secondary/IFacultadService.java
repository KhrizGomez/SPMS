package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.FacultadDTO;
import java.util.List;

public interface IFacultadService {
    List<FacultadDTO> obtenerTodasLasFacultades();
    FacultadDTO guardarFacultad(FacultadDTO facultadDTO);
}
