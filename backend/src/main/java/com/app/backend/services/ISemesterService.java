package com.app.backend.services;

import java.util.List;
import com.app.backend.dtos.SemesterDTO;

public interface ISemesterService {
    List<SemesterDTO> getAllSemesters();
    SemesterDTO setSemester(SemesterDTO semesterDTO);
}
