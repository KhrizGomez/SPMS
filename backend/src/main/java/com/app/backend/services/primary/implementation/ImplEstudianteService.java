package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.EstudianteDTO;
import com.app.backend.entities.primary.Estudiante;
import com.app.backend.repositories.primary.*;
import com.app.backend.services.primary.IEstudianteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para Estudiante del sistema principal.
 */
@Service
@RequiredArgsConstructor
public class ImplEstudianteService implements IEstudianteService {

    private final IEstudianteRepository estudianteRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ICarreraRepository carreraRepository;
    private final ISemestreRepository semestreRepository;

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<EstudianteDTO> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public EstudianteDTO obtenerPorId(Integer id) {
        return estudianteRepository.findById(id)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + id));
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public EstudianteDTO obtenerPorIdUsuario(Integer idUsuario) {
        return estudianteRepository.findByUsuario_IdUsuario(idUsuario)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado para usuario: " + idUsuario));
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<EstudianteDTO> obtenerPorCarrera(Integer idCarrera) {
        return estudianteRepository.findByCarrera_IdCarrera(idCarrera).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public EstudianteDTO crearEstudiante(@NonNull EstudianteDTO dto) {
        Estudiante entidad = new Estudiante();

        if (dto.getIdUsuario() != null) {
            usuarioRepository.findById(dto.getIdUsuario())
                    .ifPresent(entidad::setUsuario);
        }

        if (dto.getIdCarrera() != null) {
            carreraRepository.findById(dto.getIdCarrera())
                    .ifPresent(entidad::setCarrera);
        }

        if (dto.getIdSemestre() != null) {
            semestreRepository.findById(dto.getIdSemestre())
                    .ifPresent(entidad::setSemestre);
        }

        entidad.setParalelo(dto.getParalelo());
        entidad.setEstadoAcademico(dto.getEstadoAcademico() != null ? dto.getEstadoAcademico() : "Regular");
        entidad.setFechaMatricula(dto.getFechaMatricula());
        entidad.setEsExterno(dto.getEsExterno() != null ? dto.getEsExterno() : false);
        entidad.setCreadoEn(new Date());
        entidad.setActualizadoEn(new Date());

        Estudiante guardado = estudianteRepository.save(entidad);
        return convertirADto(guardado);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public EstudianteDTO actualizarEstudiante(Integer id, @NonNull EstudianteDTO dto) {
        Estudiante entidad = estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado: " + id));

        if (dto.getIdCarrera() != null) {
            carreraRepository.findById(dto.getIdCarrera())
                    .ifPresent(entidad::setCarrera);
        }

        if (dto.getIdSemestre() != null) {
            semestreRepository.findById(dto.getIdSemestre())
                    .ifPresent(entidad::setSemestre);
        }

        entidad.setParalelo(dto.getParalelo());
        entidad.setEstadoAcademico(dto.getEstadoAcademico());
        entidad.setFechaMatricula(dto.getFechaMatricula());
        entidad.setEsExterno(dto.getEsExterno());
        entidad.setActualizadoEn(new Date());

        Estudiante actualizado = estudianteRepository.save(entidad);
        return convertirADto(actualizado);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public void eliminarEstudiante(Integer id) {
        if (!estudianteRepository.existsById(id)) {
            throw new RuntimeException("Estudiante no encontrado: " + id);
        }
        estudianteRepository.deleteById(id);
    }

    private EstudianteDTO convertirADto(@NonNull Estudiante entidad) {
        EstudianteDTO dto = new EstudianteDTO();
        dto.setIdEstudiante(entidad.getIdEstudiante());

        if (entidad.getUsuario() != null) {
            dto.setIdUsuario(entidad.getUsuario().getIdUsuario());
            dto.setNombresUsuario(entidad.getUsuario().getNombres());
            dto.setApellidosUsuario(entidad.getUsuario().getApellidos());
            dto.setCedulaUsuario(entidad.getUsuario().getCedula());
        }

        if (entidad.getCarrera() != null) {
            dto.setIdCarrera(entidad.getCarrera().getIdCarrera());
            dto.setNombreCarrera(entidad.getCarrera().getNombreCarrera());
            dto.setCodigoCarrera(entidad.getCarrera().getCodigoCarrera());
        }

        if (entidad.getSemestre() != null) {
            dto.setIdSemestre(entidad.getSemestre().getIdSemestre());
            dto.setCodigoPeriodo(entidad.getSemestre().getCodigoPeriodo());
        }

        dto.setParalelo(entidad.getParalelo());
        dto.setEstadoAcademico(entidad.getEstadoAcademico());
        dto.setFechaMatricula(entidad.getFechaMatricula());
        dto.setEsExterno(entidad.getEsExterno());
        dto.setIdEstudianteSga(entidad.getIdEstudianteSga());

        return dto;
    }
}

