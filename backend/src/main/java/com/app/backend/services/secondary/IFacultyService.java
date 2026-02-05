package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.FacultyDTO;
import java.util.List;

public interface IFacultyService {
    List<FacultyDTO> getAllFaculties();
    FacultyDTO setFaculty(FacultyDTO facultyDTO);
}
