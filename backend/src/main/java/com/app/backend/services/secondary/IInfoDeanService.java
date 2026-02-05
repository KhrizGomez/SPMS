package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.InfoDeanDTO;
import java.util.List;

public interface IInfoDeanService {
    List<InfoDeanDTO> getAllInfoDeans();
    InfoDeanDTO setInfoDean(InfoDeanDTO infoDeanDTO);
}
