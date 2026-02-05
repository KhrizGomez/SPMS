package com.app.backend.services.implementation;

import com.app.backend.dtos.InfoCoordinatorDTO;
import com.app.backend.entities.secondary.InfoCoordinator;
import com.app.backend.repositories.secondary.ICareerRepository;
import com.app.backend.repositories.secondary.IInfoCoordinatorRepository;
import com.app.backend.repositories.secondary.IUserRepository;
import com.app.backend.services.IInfoCoordinatorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImplInfoCoordinatorService implements IInfoCoordinatorService {
    private final IInfoCoordinatorRepository infoCoordinatorRepository;
    private final IUserRepository userRepository;
    private final ICareerRepository careerRepository;

    @Override
    public List<InfoCoordinatorDTO> getAllInfoCoordinators() {
        List<InfoCoordinator> entities = infoCoordinatorRepository.findAll();
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public InfoCoordinatorDTO setInfoCoordinator(@NonNull InfoCoordinatorDTO infoCoordinatorDTO) {
        if (infoCoordinatorDTO.getIdUsuario() == null) {
            throw new IllegalArgumentException("El ID de usuario es requerido");
        }

        // Buscar si ya existe un coordinador con ese ID de usuario, si no, crear uno nuevo
        InfoCoordinator entity = infoCoordinatorRepository.findById(infoCoordinatorDTO.getIdUsuario())
                .orElseGet(InfoCoordinator::new);

        // Copiar propiedades del DTO (excepto las relaciones)
        entity.setOficinaAtencion(infoCoordinatorDTO.getOficinaAtencion());
        entity.setEstado(infoCoordinatorDTO.getEstado());

        // Establecer las relaciones
        userRepository.findById(infoCoordinatorDTO.getIdUsuario()).ifPresent(entity::setCoordinatorUser);
        if (infoCoordinatorDTO.getIdCarrera() != null) {
            careerRepository.findById(infoCoordinatorDTO.getIdCarrera()).ifPresent(entity::setFKInfoCarreraCoordinator);
        }

        InfoCoordinator savedEntity = infoCoordinatorRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private InfoCoordinatorDTO convertToDto(@NonNull InfoCoordinator entity) {
        InfoCoordinatorDTO dto = new InfoCoordinatorDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getFKInfoCarreraCoordinator() != null) {
            dto.setIdCarrera(entity.getFKInfoCarreraCoordinator().getIdCarrera());
        }
        return dto;
    }
}
