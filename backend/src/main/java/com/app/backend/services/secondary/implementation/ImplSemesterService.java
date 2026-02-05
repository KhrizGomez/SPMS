package com.app.backend.services.secondary.implementation;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import com.app.backend.dtos.secondary.SemesterDTO;
import com.app.backend.entities.secondary.Semester;
import com.app.backend.repositories.secondary.ISemesterRepository;
import com.app.backend.services.secondary.ISemesterService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImplSemesterService implements ISemesterService {
    private final ISemesterRepository semesterRepository;

    @Override
    public List<SemesterDTO> getAllSemesters() {
        List<Semester> entities = semesterRepository.findAll();
        return entities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SemesterDTO setSemester(@NonNull SemesterDTO semesterDTO) {
        Semester entity = new Semester();
        BeanUtils.copyProperties(semesterDTO, entity);
        Semester savedEntity = semesterRepository.save(entity);
        return convertToDto(savedEntity);
    }

    private SemesterDTO convertToDto(@NonNull Semester entity) {
        SemesterDTO dto = new SemesterDTO();
        BeanUtils.copyProperties(entity, dto);
        return dto;
    }
}
