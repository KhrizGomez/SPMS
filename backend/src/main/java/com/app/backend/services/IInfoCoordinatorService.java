package com.app.backend.services;

import com.app.backend.dtos.InfoCoordinatorDTO;
import java.util.List;

public interface IInfoCoordinatorService {
    List<InfoCoordinatorDTO> getAllInfoCoordinators();
    InfoCoordinatorDTO setInfoCoordinator(InfoCoordinatorDTO infoCoordinatorDTO);
}
