package com.app.backend.services.secondary;

import com.app.backend.dtos.secondary.InfoStudentDTO;
import java.util.List;

public interface IInfoStudentService {
    List<InfoStudentDTO> getAllInfoStudents();
    InfoStudentDTO setInfoStudent(InfoStudentDTO infoStudentDTO);
}
