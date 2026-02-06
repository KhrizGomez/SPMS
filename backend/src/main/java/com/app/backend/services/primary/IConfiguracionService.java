package com.app.backend.services.primary;

import com.app.backend.dtos.primary.ConfiguracionDTO;
import java.util.List;

public interface IConfiguracionService {
    List<ConfiguracionDTO> obtenerTodasLasConfiguraciones();
    ConfiguracionDTO obtenerConfiguracionPorId(Integer id);
    ConfiguracionDTO crearConfiguracion(ConfiguracionDTO configuracionDTO);
    ConfiguracionDTO actualizarConfiguracion(Integer id, ConfiguracionDTO configuracionDTO);
    void eliminarConfiguracion(Integer id);
}
