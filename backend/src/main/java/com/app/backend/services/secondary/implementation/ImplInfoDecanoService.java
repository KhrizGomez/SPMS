package com.app.backend.services.secondary.implementation;

import com.app.backend.dtos.secondary.InfoDecanoDTO;
import com.app.backend.entities.secondary.InfoDecano;
import com.app.backend.repositories.secondary.IFacultadRepository;
import com.app.backend.repositories.secondary.IInfoDecanoRepository;
import com.app.backend.repositories.secondary.IUsuarioSecundarioRepository;
import com.app.backend.services.secondary.IInfoDecanoService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para InfoDecano.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta.
 */
@Service
@RequiredArgsConstructor
public class ImplInfoDecanoService implements IInfoDecanoService {
    private final IInfoDecanoRepository infoDecanoRepository;
    private final IUsuarioSecundarioRepository usuarioRepository;
    private final IFacultadRepository facultadRepository;

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<InfoDecanoDTO> obtenerTodosLosInfoDecanos() {
        List<InfoDecano> entidades = infoDecanoRepository.findAll();
        return entidades.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager")
    public InfoDecanoDTO guardarInfoDecano(@NonNull InfoDecanoDTO infoDecanoDTO) {
        if (infoDecanoDTO.getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido");
        }

        InfoDecano entidad = infoDecanoRepository.findByUsuario_IdUsuario(infoDecanoDTO.getIdUsuario())
                .orElseGet(InfoDecano::new);

        entidad.setFechaNombramiento(infoDecanoDTO.getFechaNombramiento());
        entidad.setFechaFinPeriodo(infoDecanoDTO.getFechaFinPeriodo());
        entidad.setResolucionNombramiento(infoDecanoDTO.getResolucionNombramiento());
        entidad.setEstado(infoDecanoDTO.getEstado());
        entidad.setObservaciones(infoDecanoDTO.getObservaciones());

        usuarioRepository.findById(infoDecanoDTO.getIdUsuario()).ifPresent(entidad::setUsuario);
        if (infoDecanoDTO.getIdFacultad() != null) {
            facultadRepository.findById(infoDecanoDTO.getIdFacultad()).ifPresent(entidad::setFacultad);
        }

        InfoDecano entidadGuardada = infoDecanoRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    private InfoDecanoDTO convertirADto(@NonNull InfoDecano entidad) {
        InfoDecanoDTO dto = new InfoDecanoDTO();
        dto.setIdDecano(entidad.getIdDecano());
        dto.setFechaNombramiento(entidad.getFechaNombramiento());
        dto.setFechaFinPeriodo(entidad.getFechaFinPeriodo());
        dto.setResolucionNombramiento(entidad.getResolucionNombramiento());
        dto.setEstado(entidad.getEstado());
        dto.setObservaciones(entidad.getObservaciones());

        if (entidad.getUsuario() != null) {
            dto.setIdUsuario(entidad.getUsuario().getIdUsuario());
        }
        if (entidad.getFacultad() != null) {
            dto.setIdFacultad(entidad.getFacultad().getIdFacultad());
            dto.setNombreFacultad(entidad.getFacultad().getNombreFacultad());
            dto.setCodigoFacultad(entidad.getFacultad().getCodigoFacultad());
        }
        return dto;
    }
}
