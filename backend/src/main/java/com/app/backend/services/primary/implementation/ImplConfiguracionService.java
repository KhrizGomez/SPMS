package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.ConfiguracionDTO;
import com.app.backend.entities.primary.Configuracion;
import com.app.backend.repositories.primary.IConfiguracionRepository;
import com.app.backend.services.primary.IConfiguracionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplConfiguracionService implements IConfiguracionService {
    private final IConfiguracionRepository configuracionRepository;

    @Override
    public List<ConfiguracionDTO> obtenerTodasLasConfiguraciones() {
        return configuracionRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public ConfiguracionDTO obtenerConfiguracionPorId(Integer id) {
        return configuracionRepository.findById(id)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Configuración no encontrada con ID: " + id));
    }

    @Override
    public ConfiguracionDTO crearConfiguracion(@NonNull ConfiguracionDTO configuracionDTO) {
        Configuracion entidad = new Configuracion();
        copiarDtoAEntidad(configuracionDTO, entidad);
        Configuracion entidadGuardada = configuracionRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    @Override
    public ConfiguracionDTO actualizarConfiguracion(Integer id, @NonNull ConfiguracionDTO configuracionDTO) {
        Configuracion entidad = configuracionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuración no encontrada con ID: " + id));
        copiarDtoAEntidad(configuracionDTO, entidad);
        Configuracion entidadGuardada = configuracionRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    @Override
    public void eliminarConfiguracion(Integer id) {
        if (!configuracionRepository.existsById(id)) {
            throw new RuntimeException("Configuración no encontrada con ID: " + id);
        }
        configuracionRepository.deleteById(id);
    }

    private ConfiguracionDTO convertirADto(@NonNull Configuracion entidad) {
        ConfiguracionDTO dto = new ConfiguracionDTO();
        dto.setIdConfiguracion(entidad.getIdConfiguracion());
        dto.setFotoPerfil(entidad.getFotoPerfil());
        dto.setFirmaEscaneada(entidad.getFirmaEscaneada());
        dto.setCanalSms(entidad.getCanalSms());
        dto.setCanalCorreo(entidad.getCanalCorreo());
        dto.setCanalWhatsapp(entidad.getCanalWhatsapp());
        return dto;
    }

    private void copiarDtoAEntidad(@NonNull ConfiguracionDTO dto, @NonNull Configuracion entidad) {
        entidad.setFotoPerfil(dto.getFotoPerfil());
        entidad.setFirmaEscaneada(dto.getFirmaEscaneada());
        entidad.setCanalSms(dto.getCanalSms());
        entidad.setCanalCorreo(dto.getCanalCorreo());
        entidad.setCanalWhatsapp(dto.getCanalWhatsapp());
    }
}
