package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.SemesterDTO;
import java.util.List;

public interface ISemesterService {
    List<SemesterDTO> getAllSemesters();
    SemesterDTO setSemester(SemesterDTO semesterDTO);
}
