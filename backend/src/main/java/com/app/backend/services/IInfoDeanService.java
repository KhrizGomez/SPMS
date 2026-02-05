package com.app.backend.services;

import com.app.backend.dtos.InfoDeanDTO;
import java.util.List;

public interface IInfoDeanService {
    List<InfoDeanDTO> getAllInfoDeans();
    InfoDeanDTO setInfoDean(InfoDeanDTO infoDeanDTO);
}
