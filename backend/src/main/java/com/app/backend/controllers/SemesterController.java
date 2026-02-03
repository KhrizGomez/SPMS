package com.app.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.backend.dtos.SemesterDTO;
import com.app.backend.services.ISemesterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/semesters")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SemesterController {
     private final ISemesterService semesterService;

    @GetMapping
    public ResponseEntity<List<SemesterDTO>> getAll(){
        return ResponseEntity.ok(semesterService.getAllSemesters());
    }

    @PostMapping
    public ResponseEntity<SemesterDTO> create(@RequestBody SemesterDTO semesterDTO){
        SemesterDTO savedSemester = semesterService.setSemester(semesterDTO);
        return ResponseEntity.ok(savedSemester);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemesterDTO> update(@PathVariable Integer id, @RequestBody SemesterDTO semesterDTO){
        semesterDTO.setIdSemestre(id);
        SemesterDTO updateSemester = semesterService.setSemester(semesterDTO);
        return ResponseEntity.ok(updateSemester);
    }
}
