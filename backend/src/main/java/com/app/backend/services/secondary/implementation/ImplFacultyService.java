package com.app.backend.services.secondary.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.app.backend.dtos.secondary.FacultyDTO;
import com.app.backend.entities.secondary.Faculty;
import com.app.backend.repositories.secondary.IFacultyRepository;
import com.app.backend.services.secondary.IFacultyService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplFacultyService implements IFacultyService {
    private final IFacultyRepository facultyRepository;

    @Override
    public List<FacultyDTO> getAllFaculties() {
        List<Faculty> entities = facultyRepository.findAll();
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FacultyDTO setFaculty(@NonNull FacultyDTO facultyDTO) {
        Faculty entity = new Faculty();
        BeanUtils.copyProperties(facultyDTO, entity);
        Faculty savedEntity = facultyRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private FacultyDTO convertToDto(@NonNull Faculty entity) {
        FacultyDTO dto = new FacultyDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
