package com.app.backend.controllers;

import com.app.backend.dtos.CareerDTO;
import com.app.backend.dtos.InfoStudentDTO;
import com.app.backend.services.IInfoStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/info-students")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoStudentController {
    private final IInfoStudentService infoStudentService;

    @GetMapping
    public ResponseEntity<List<InfoStudentDTO>> getAll(){
        return ResponseEntity.ok(infoStudentService.getAllInfoStudents());
    }

    @PostMapping
    public ResponseEntity<InfoStudentDTO> create(@RequestBody InfoStudentDTO infoStudentDTO){
        InfoStudentDTO savedInfoStudent = infoStudentService.setInfoStudent(infoStudentDTO);
        return ResponseEntity.ok(savedInfoStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoStudentDTO> update(@PathVariable Integer id, @RequestBody InfoStudentDTO infoStudentDTO){
        infoStudentDTO.setIdUsuario(id);
        InfoStudentDTO updateInfoStudent = infoStudentService.setInfoStudent(infoStudentDTO);
        return ResponseEntity.ok(updateInfoStudent);
    }
}
