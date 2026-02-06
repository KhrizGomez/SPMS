package com.app.backend.services.secondary.implementation;

import com.app.backend.dtos.secondary.InfoCoordinadorDTO;
import com.app.backend.entities.secondary.InfoCoordinador;
import com.app.backend.repositories.secondary.ICarreraRepository;
import com.app.backend.repositories.secondary.IInfoCoordinadorRepository;
import com.app.backend.repositories.secondary.IUsuarioSecundarioRepository;
import com.app.backend.services.secondary.IInfoCoordinadorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para InfoCoordinador.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta.
 */
@Service
@RequiredArgsConstructor
public class ImplInfoCoordinadorService implements IInfoCoordinadorService {
    private final IInfoCoordinadorRepository infoCoordinadorRepository;
    private final IUsuarioSecundarioRepository usuarioRepository;
    private final ICarreraRepository carreraRepository;

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<InfoCoordinadorDTO> obtenerTodosLosInfoCoordinadores() {
        List<InfoCoordinador> entidades = infoCoordinadorRepository.findAll();
        return entidades.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager")
    public InfoCoordinadorDTO guardarInfoCoordinador(@NonNull InfoCoordinadorDTO infoCoordinadorDTO) {
        if (infoCoordinadorDTO.getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido");
        }

        InfoCoordinador entidad = infoCoordinadorRepository.findByUsuario_IdUsuario(infoCoordinadorDTO.getIdUsuario())
                .orElseGet(InfoCoordinador::new);

        entidad.setOficinaAtencion(infoCoordinadorDTO.getOficinaAtencion());
        entidad.setHorarioAtencion(infoCoordinadorDTO.getHorarioAtencion());
        entidad.setExtensionTelefonica(infoCoordinadorDTO.getExtensionTelefonica());
        entidad.setFechaNombramiento(infoCoordinadorDTO.getFechaNombramiento());
        entidad.setFechaFinPeriodo(infoCoordinadorDTO.getFechaFinPeriodo());
        entidad.setEstado(infoCoordinadorDTO.getEstado());
        entidad.setObservaciones(infoCoordinadorDTO.getObservaciones());

        usuarioRepository.findById(infoCoordinadorDTO.getIdUsuario()).ifPresent(entidad::setUsuario);
        if (infoCoordinadorDTO.getIdCarrera() != null) {
            carreraRepository.findById(infoCoordinadorDTO.getIdCarrera()).ifPresent(entidad::setCarrera);
        }

        InfoCoordinador entidadGuardada = infoCoordinadorRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    private InfoCoordinadorDTO convertirADto(@NonNull InfoCoordinador entidad) {
        InfoCoordinadorDTO dto = new InfoCoordinadorDTO();
        dto.setIdCoordinador(entidad.getIdCoordinador());
        dto.setOficinaAtencion(entidad.getOficinaAtencion());
        dto.setHorarioAtencion(entidad.getHorarioAtencion());
        dto.setExtensionTelefonica(entidad.getExtensionTelefonica());
        dto.setFechaNombramiento(entidad.getFechaNombramiento());
        dto.setFechaFinPeriodo(entidad.getFechaFinPeriodo());
        dto.setEstado(entidad.getEstado());
        dto.setObservaciones(entidad.getObservaciones());

        if (entidad.getUsuario() != null) {
            dto.setIdUsuario(entidad.getUsuario().getIdUsuario());
        }
        if (entidad.getCarrera() != null) {
            dto.setIdCarrera(entidad.getCarrera().getIdCarrera());
            dto.setNombreCarrera(entidad.getCarrera().getNombreCarrera());
            dto.setCodigoCarrera(entidad.getCarrera().getCodigoCarrera());
        }
        return dto;
    }
}
