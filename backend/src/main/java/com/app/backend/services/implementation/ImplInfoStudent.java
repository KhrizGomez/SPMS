package com.app.backend.services.implementation;

import com.app.backend.dtos.InfoStudentDTO;
import com.app.backend.entities.secondary.InfoStudent;
import com.app.backend.repositories.secondary.ICareerRepository;
import com.app.backend.repositories.secondary.IInfoStudentRepository;
import com.app.backend.repositories.secondary.ISemesterRepository;
import com.app.backend.repositories.secondary.IUserRepository;
import com.app.backend.services.IInfoStudentService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplInfoStudent implements IInfoStudentService {
    private final IInfoStudentRepository infoStudentRepository;
    private final IUserRepository userRepository;
    private final ICareerRepository careerRepository;
    private final ISemesterRepository semesterRepository;

    @Override
    public List<InfoStudentDTO> getAllInfoStudents() {
        List<InfoStudent> entities = infoStudentRepository.findAll();
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InfoStudentDTO setInfoStudent(@NonNull InfoStudentDTO infoStudentDTO) {
        if (infoStudentDTO.getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido");
        }

        // Buscar si ya existe un estudiante con ese ID de usuario, si no, crear uno nuevo
        InfoStudent entity = infoStudentRepository.findById(infoStudentDTO.getIdUsuario())
                .orElseGet(InfoStudent::new);

        // Copiar propiedades del DTO (excepto las relaciones)
        entity.setFechaIngreso(infoStudentDTO.getFechaIngreso());
        entity.setEstadoAcademico(infoStudentDTO.getEstadoAcademico());

        // Establecer las relaciones
        userRepository.findById(infoStudentDTO.getIdUsuario()).ifPresent(entity::setFKStudentUser);
        if (infoStudentDTO.getIdCarrera() != null) {
            careerRepository.findById(infoStudentDTO.getIdCarrera()).ifPresent(entity::setFKInfoCarreraStudent);
        }
        if (infoStudentDTO.getIdSemestre() != null) {
            semesterRepository.findById(infoStudentDTO.getIdSemestre()).ifPresent(entity::setFKInfoSemesterStudent);
        }

        InfoStudent savedEntity = infoStudentRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private InfoStudentDTO convertToDto(@NonNull InfoStudent entity) {
        InfoStudentDTO dto = new InfoStudentDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getFKInfoCarreraStudent() != null && entity.getFKInfoSemesterStudent() != null) {
            dto.setIdCarrera(entity.getFKInfoCarreraStudent().getIdCarrera());
            dto.setIdSemestre(entity.getFKInfoSemesterStudent().getIdSemestre());
        }
        return dto;
    }
}
