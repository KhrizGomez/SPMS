package com.app.backend.services.secondary.implementation;

import com.app.backend.dtos.secondary.InfoEstudianteDTO;
import com.app.backend.entities.secondary.InfoEstudiante;
import com.app.backend.repositories.secondary.ICarreraRepository;
import com.app.backend.repositories.secondary.IInfoEstudianteRepository;
import com.app.backend.repositories.secondary.ISemestreRepository;
import com.app.backend.repositories.secondary.IUsuarioSecundarioRepository;
import com.app.backend.services.secondary.IInfoEstudianteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para InfoEstudiante.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta.
 */
@Service
@RequiredArgsConstructor
public class ImplInfoEstudianteService implements IInfoEstudianteService {
    private final IInfoEstudianteRepository infoEstudianteRepository;
    private final IUsuarioSecundarioRepository usuarioRepository;
    private final ICarreraRepository carreraRepository;
    private final ISemestreRepository semestreRepository;

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<InfoEstudianteDTO> obtenerTodosLosInfoEstudiantes() {
        List<InfoEstudiante> entidades = infoEstudianteRepository.findAll();
        return entidades.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager")
    public InfoEstudianteDTO guardarInfoEstudiante(@NonNull InfoEstudianteDTO dto) {
        if (dto.getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido");
        }

        InfoEstudiante entidad = infoEstudianteRepository.findByUsuario_IdUsuario(dto.getIdUsuario())
                .orElseGet(InfoEstudiante::new);

        // Asignar usuario
        usuarioRepository.findById(dto.getIdUsuario()).ifPresent(entidad::setUsuario);

        // Asignar carrera
        if (dto.getIdCarrera() != null) {
            carreraRepository.findById(dto.getIdCarrera()).ifPresent(entidad::setCarrera);
        }

        // Asignar semestre actual
        if (dto.getIdSemestreActual() != null) {
            semestreRepository.findById(dto.getIdSemestreActual()).ifPresent(entidad::setSemestreActual);
        }

        // Copiar campos simples
        entidad.setNumeroMatricula(dto.getNumeroMatricula());
        entidad.setParalelo(dto.getParalelo());
        entidad.setJornada(dto.getJornada());
        entidad.setFechaIngreso(dto.getFechaIngreso());
        entidad.setFechaEgreso(dto.getFechaEgreso());
        entidad.setEstadoAcademico(dto.getEstadoAcademico());
        entidad.setPromedioGeneral(dto.getPromedioGeneral());
        entidad.setCreditosAprobados(dto.getCreditosAprobados());
        entidad.setCreditosTotales(dto.getCreditosTotales());
        entidad.setMatriculado(dto.getMatriculado());
        entidad.setEsBecado(dto.getEsBecado());
        entidad.setTipoBeca(dto.getTipoBeca());
        entidad.setObservaciones(dto.getObservaciones());

        InfoEstudiante entidadGuardada = infoEstudianteRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    private InfoEstudianteDTO convertirADto(@NonNull InfoEstudiante entidad) {
        InfoEstudianteDTO dto = new InfoEstudianteDTO();
        dto.setIdEstudiante(entidad.getIdEstudiante());

        if (entidad.getUsuario() != null) {
            dto.setIdUsuario(entidad.getUsuario().getIdUsuario());
        }

        if (entidad.getCarrera() != null) {
            dto.setIdCarrera(entidad.getCarrera().getIdCarrera());
            dto.setNombreCarrera(entidad.getCarrera().getNombreCarrera());
            dto.setCodigoCarrera(entidad.getCarrera().getCodigoCarrera());
        }

        if (entidad.getSemestreActual() != null) {
            dto.setIdSemestreActual(entidad.getSemestreActual().getIdSemestre());
            dto.setCodigoPeriodo(entidad.getSemestreActual().getCodigoPeriodo());
        }

        dto.setNumeroMatricula(entidad.getNumeroMatricula());
        dto.setParalelo(entidad.getParalelo());
        dto.setJornada(entidad.getJornada());
        dto.setFechaIngreso(entidad.getFechaIngreso());
        dto.setFechaEgreso(entidad.getFechaEgreso());
        dto.setEstadoAcademico(entidad.getEstadoAcademico());
        dto.setPromedioGeneral(entidad.getPromedioGeneral());
        dto.setCreditosAprobados(entidad.getCreditosAprobados());
        dto.setCreditosTotales(entidad.getCreditosTotales());
        dto.setMatriculado(entidad.getMatriculado());
        dto.setEsBecado(entidad.getEsBecado());
        dto.setTipoBeca(entidad.getTipoBeca());
        dto.setObservaciones(entidad.getObservaciones());

        return dto;
    }
}
