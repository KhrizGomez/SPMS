package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.InfoStudentDTO;
import com.app.backend.services.secondary.IInfoStudentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/info-students")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoStudentController {
    private final IInfoStudentService infoStudentService;

    @GetMapping
    public ResponseEntity<List<InfoStudentDTO>> getAll() {
        return ResponseEntity.ok(infoStudentService.getAllInfoStudents());
    }

    @PostMapping
    public ResponseEntity<InfoStudentDTO> create(@RequestBody InfoStudentDTO infoStudentDTO) {
        InfoStudentDTO savedInfoStudent = infoStudentService.setInfoStudent(infoStudentDTO);
        return ResponseEntity.ok(savedInfoStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoStudentDTO> update(@PathVariable Integer id, @RequestBody InfoStudentDTO infoStudentDTO) {
        infoStudentDTO.setIdUsuario(id);
        InfoStudentDTO updateInfoStudent = infoStudentService.setInfoStudent(infoStudentDTO);
        return ResponseEntity.ok(updateInfoStudent);
    }
}
