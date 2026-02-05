package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.InfoCoordinatorDTO;
import java.util.List;

public interface IInfoCoordinatorService {
    List<InfoCoordinatorDTO> getAllInfoCoordinators();
    InfoCoordinatorDTO setInfoCoordinator(InfoCoordinatorDTO infoCoordinatorDTO);
}
