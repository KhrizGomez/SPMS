package com.app.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.backend.dtos.FacultyDTO;
import com.app.backend.services.IFacultyService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/facultys")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FacultyController {
    private final IFacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<FacultyDTO>> getAll(){
        return ResponseEntity.ok(facultyService.getAllFacultys());
    }

    @PostMapping
    public ResponseEntity<FacultyDTO> create(@RequestBody FacultyDTO facultyDTO){
        FacultyDTO savedFaculty = facultyService.setFaculty(facultyDTO);
        return ResponseEntity.ok(savedFaculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDTO> update(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO){
        facultyDTO.setIdFacultad(id);
        FacultyDTO updateFaculty = facultyService.setFaculty(facultyDTO);
        return ResponseEntity.ok(updateFaculty);
    }
}
