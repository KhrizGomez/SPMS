package com.app.backend.services;

import com.app.backend.dtos.InfoStudentDTO;

import java.util.List;

public interface IInfoStudentService {
    List<InfoStudentDTO> getAllInfoStudents();
    InfoStudentDTO setInfoStudent(InfoStudentDTO infoStudentDTO);
}
