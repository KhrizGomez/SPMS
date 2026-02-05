package com.app.backend.services.implementation;

import com.app.backend.dtos.InfoDeanDTO;
import com.app.backend.entities.secondary.InfoDean;
import com.app.backend.repositories.secondary.IFacultyRepository;
import com.app.backend.repositories.secondary.IInfoDeanRepository;
import com.app.backend.repositories.secondary.IUserRepository;
import com.app.backend.services.IInfoDeanService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplInfoDeanService implements IInfoDeanService {
    private final IInfoDeanRepository infoDeanRepository;
    private final IUserRepository userRepository;
    private final IFacultyRepository facultyRepository;

    @Override
    public List<InfoDeanDTO> getAllInfoDeans() {
        List<InfoDean> entities = infoDeanRepository.findAll();
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InfoDeanDTO setInfoDean(@NonNull InfoDeanDTO infoDeanDTO) {
        if (infoDeanDTO.getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido");
        }

        // Buscar si ya existe un decano con ese ID de usuario, si no, crear uno nuevo
        InfoDean entity = infoDeanRepository.findById(infoDeanDTO.getIdUsuario())
                .orElseGet(InfoDean::new);

        // Copiar propiedades del DTO (excepto las relaciones)
        entity.setFechaNombramiento(infoDeanDTO.getFechaNombramiento());
        entity.setEstado(infoDeanDTO.getEstado());

        // Establecer las relaciones
        userRepository.findById(infoDeanDTO.getIdUsuario()).ifPresent(entity::setFKDeanUser);
        if (infoDeanDTO.getIdFacultad() != null) {
            facultyRepository.findById(infoDeanDTO.getIdFacultad()).ifPresent(entity::setFKInfoFacultadDean);
        }

        InfoDean savedEntity = infoDeanRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private InfoDeanDTO convertToDto(@NonNull InfoDean entity) {
        InfoDeanDTO dto = new InfoDeanDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getFKInfoFacultadDean() != null) {
            dto.setIdFacultad(entity.getFKInfoFacultadDean().getIdFacultad());
        }
        return dto;
    }
}
