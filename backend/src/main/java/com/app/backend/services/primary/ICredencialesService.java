package com.app.backend.services.primary;

import com.app.backend.dtos.primary.CredencialesDTO;
import java.util.List;

public interface ICredencialesService {
    List<CredencialesDTO> obtenerTodasLasCredenciales();
    CredencialesDTO obtenerCredencialesPorId(Integer id);
    CredencialesDTO obtenerCredencialesPorIdUsuario(Integer idUsuario);
    CredencialesDTO crearCredenciales(CredencialesDTO credencialesDTO);
    CredencialesDTO actualizarCredenciales(Integer id, CredencialesDTO credencialesDTO);
    void eliminarCredenciales(Integer id);
}
