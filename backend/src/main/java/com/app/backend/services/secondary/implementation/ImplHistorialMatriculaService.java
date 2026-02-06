package com.app.backend.services.secondary.implementation;

import com.app.backend.dtos.secondary.HistorialMatriculaDTO;
import com.app.backend.entities.secondary.HistorialMatricula;
import com.app.backend.repositories.secondary.ICarreraRepository;
import com.app.backend.repositories.secondary.IHistorialMatriculaRepository;
import com.app.backend.repositories.secondary.IInfoEstudianteRepository;
import com.app.backend.repositories.secondary.ISemestreRepository;
import com.app.backend.services.secondary.IHistorialMatriculaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para HistorialMatricula.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta.
 */
@Service
@RequiredArgsConstructor
public class ImplHistorialMatriculaService implements IHistorialMatriculaService {

    private final IHistorialMatriculaRepository historialRepository;
    private final IInfoEstudianteRepository estudianteRepository;
    private final ISemestreRepository semestreRepository;
    private final ICarreraRepository carreraRepository;

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<HistorialMatriculaDTO> obtenerTodosLosHistoriales() {
        return historialRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<HistorialMatriculaDTO> obtenerPorEstudiante(Integer idEstudiante) {
        return historialRepository.findByEstudiante_IdEstudiante(idEstudiante).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<HistorialMatriculaDTO> obtenerPorSemestre(Integer idSemestre) {
        return historialRepository.findBySemestre_IdSemestre(idSemestre).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<HistorialMatriculaDTO> obtenerPorCarrera(Integer idCarrera) {
        return historialRepository.findByCarrera_IdCarrera(idCarrera).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager")
    public HistorialMatriculaDTO guardarHistorial(@NonNull HistorialMatriculaDTO dto) {
        HistorialMatricula entidad = new HistorialMatricula();

        if (dto.getIdEstudiante() != null) {
            estudianteRepository.findById(dto.getIdEstudiante())
                    .ifPresent(entidad::setEstudiante);
        }

        if (dto.getIdSemestre() != null) {
            semestreRepository.findById(dto.getIdSemestre())
                    .ifPresent(entidad::setSemestre);
        }

        if (dto.getIdCarrera() != null) {
            carreraRepository.findById(dto.getIdCarrera())
                    .ifPresent(entidad::setCarrera);
        }

        entidad.setParalelo(dto.getParalelo());
        entidad.setFechaMatricula(dto.getFechaMatricula() != null ? dto.getFechaMatricula() : new Date());
        entidad.setTipoMatricula(dto.getTipoMatricula() != null ? dto.getTipoMatricula() : "Ordinaria");
        entidad.setEstadoMatricula(dto.getEstadoMatricula() != null ? dto.getEstadoMatricula() : "Activa");
        entidad.setObservaciones(dto.getObservaciones());
        entidad.setCreadoEn(new Date());

        HistorialMatricula guardado = historialRepository.save(entidad);
        return convertirADto(guardado);
    }

    private HistorialMatriculaDTO convertirADto(@NonNull HistorialMatricula entidad) {
        HistorialMatriculaDTO dto = new HistorialMatriculaDTO();
        dto.setIdHistorial(entidad.getIdHistorial());

        if (entidad.getEstudiante() != null) {
            dto.setIdEstudiante(entidad.getEstudiante().getIdEstudiante());
            dto.setNumeroMatricula(entidad.getEstudiante().getNumeroMatricula());
            if (entidad.getEstudiante().getUsuario() != null) {
                dto.setNombreEstudiante(entidad.getEstudiante().getUsuario().getNombres() + " " +
                                        entidad.getEstudiante().getUsuario().getApellidos());
            }
        }

        if (entidad.getSemestre() != null) {
            dto.setIdSemestre(entidad.getSemestre().getIdSemestre());
            dto.setCodigoPeriodo(entidad.getSemestre().getCodigoPeriodo());
        }

        if (entidad.getCarrera() != null) {
            dto.setIdCarrera(entidad.getCarrera().getIdCarrera());
            dto.setNombreCarrera(entidad.getCarrera().getNombreCarrera());
        }

        dto.setParalelo(entidad.getParalelo());
        dto.setFechaMatricula(entidad.getFechaMatricula());
        dto.setTipoMatricula(entidad.getTipoMatricula());
        dto.setEstadoMatricula(entidad.getEstadoMatricula());
        dto.setObservaciones(entidad.getObservaciones());
        dto.setCreadoEn(entidad.getCreadoEn());

        return dto;
    }
}

