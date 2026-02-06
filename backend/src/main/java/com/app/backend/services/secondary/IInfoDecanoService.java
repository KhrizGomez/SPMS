package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.InfoDecanoDTO;
import java.util.List;

public interface IInfoDecanoService {
    List<InfoDecanoDTO> obtenerTodosLosInfoDecanos();
    InfoDecanoDTO guardarInfoDecano(InfoDecanoDTO infoDecanoDTO);
}
