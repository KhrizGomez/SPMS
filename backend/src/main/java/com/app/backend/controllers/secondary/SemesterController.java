package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.SemesterDTO;
import com.app.backend.services.secondary.ISemesterService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/semesters")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SemesterController {
    private final ISemesterService semesterService;

    @GetMapping
    public ResponseEntity<List<SemesterDTO>> getAll() {
        return ResponseEntity.ok(semesterService.getAllSemesters());
    }

    @PostMapping
    public ResponseEntity<SemesterDTO> create(@RequestBody SemesterDTO semesterDTO) {
        SemesterDTO savedSemester = semesterService.setSemester(semesterDTO);
        return ResponseEntity.ok(savedSemester);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemesterDTO> update(@PathVariable Integer id, @RequestBody SemesterDTO semesterDTO) {
        semesterDTO.setIdSemestre(id);
        SemesterDTO updateSemester = semesterService.setSemester(semesterDTO);
        return ResponseEntity.ok(updateSemester);
    }
}
