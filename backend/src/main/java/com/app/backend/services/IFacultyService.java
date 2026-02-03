package com.app.backend.services;
import java.util.List;
import com.app.backend.dtos.FacultyDTO;;

public interface IFacultyService {
    List<FacultyDTO> getAllFacultys();
    FacultyDTO setFaculty(FacultyDTO facultyDTO);
}
