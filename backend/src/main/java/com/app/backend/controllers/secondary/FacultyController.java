package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.FacultyDTO;
import com.app.backend.services.secondary.IFacultyService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/faculties")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FacultyController {
    private final IFacultyService facultyService;

    @GetMapping
    public ResponseEntity<List<FacultyDTO>> getAll() {
        return ResponseEntity.ok(facultyService.getAllFaculties());
    }

    @PostMapping
    public ResponseEntity<FacultyDTO> create(@RequestBody FacultyDTO facultyDTO) {
        FacultyDTO savedFaculty = facultyService.setFaculty(facultyDTO);
        return ResponseEntity.ok(savedFaculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDTO> update(@PathVariable Integer id, @RequestBody FacultyDTO facultyDTO) {
        facultyDTO.setIdFacultad(id);
        FacultyDTO updateFaculty = facultyService.setFaculty(facultyDTO);
        return ResponseEntity.ok(updateFaculty);
    }
}
