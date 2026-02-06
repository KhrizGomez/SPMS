package com.app.backend.services.secondary.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.app.backend.dtos.secondary.FacultadDTO;
import com.app.backend.entities.secondary.Facultad;
import com.app.backend.repositories.secondary.IFacultadRepository;
import com.app.backend.services.secondary.IFacultadService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplFacultadService implements IFacultadService {
    private final IFacultadRepository facultadRepository;

    @Override
    public List<FacultadDTO> obtenerTodasLasFacultades() {
        List<Facultad> entidades = facultadRepository.findAll();
        return entidades.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public FacultadDTO guardarFacultad(@NonNull FacultadDTO facultadDTO) {
        Facultad entidad = new Facultad();
        entidad.setNombreFacultad(facultadDTO.getNombreFacultad());
        entidad.setCodigoFacultad(facultadDTO.getCodigoFacultad());
        entidad.setUbicacionOficina(facultadDTO.getUbicacionOficina());
        entidad.setTelefonoOficina(facultadDTO.getTelefonoOficina());
        entidad.setEmailFacultad(facultadDTO.getEmailFacultad());
        entidad.setFechaCreacion(facultadDTO.getFechaCreacion());
        entidad.setEstado(facultadDTO.getEstado());

        Facultad entidadGuardada = facultadRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    private FacultadDTO convertirADto(@NonNull Facultad entidad) {
        FacultadDTO dto = new FacultadDTO();
        dto.setIdFacultad(entidad.getIdFacultad());
        dto.setNombreFacultad(entidad.getNombreFacultad());
        dto.setCodigoFacultad(entidad.getCodigoFacultad());
        dto.setUbicacionOficina(entidad.getUbicacionOficina());
        dto.setTelefonoOficina(entidad.getTelefonoOficina());
        dto.setEmailFacultad(entidad.getEmailFacultad());
        dto.setFechaCreacion(entidad.getFechaCreacion());
        dto.setEstado(entidad.getEstado());
        return dto;
    }
}
