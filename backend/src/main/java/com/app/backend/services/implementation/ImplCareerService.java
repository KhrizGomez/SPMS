package com.app.backend.services.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.app.backend.dtos.CareerDTO;
import com.app.backend.entities.secondary.Career;
import com.app.backend.repositories.secondary.ICareerRepository;
import com.app.backend.repositories.secondary.IFacultyRepository;
import com.app.backend.services.ICareerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplCareerService implements ICareerService {
    private final ICareerRepository careerRepository;
    private final IFacultyRepository facultyRepository;

    @Override
    public List<CareerDTO> getAllCaeers() {
        List<Career> entities = careerRepository.findAll();
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CareerDTO setCareer(@NonNull CareerDTO careerDTO) {
        Career entity = new Career();
        BeanUtils.copyProperties(careerDTO, entity);
        if (careerDTO.getIdFacultad() != null) {
            facultyRepository.findById(careerDTO.getIdFacultad()).ifPresent(entity::setFKCarreraFacultad);
        }
        Career savedEntity = careerRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private CareerDTO convertToDto(@NonNull Career entity) {
        CareerDTO dto = new CareerDTO();
        BeanUtils.copyProperties(entity, dto);
        if (entity.getFKCarreraFacultad() != null) {
            dto.setIdFacultad(entity.getFKCarreraFacultad().getIdFacultad());
            dto.setNombreFacultad(entity.getFKCarreraFacultad().getNombreFacultad());
        }
        return dto;
    }
}
