package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.FacultadDTO;
import com.app.backend.services.secondary.IFacultadService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/facultades")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class FacultadController {
    private final IFacultadService facultadService;

    @GetMapping
    public ResponseEntity<List<FacultadDTO>> obtenerTodas() {
        return ResponseEntity.ok(facultadService.obtenerTodasLasFacultades());
    }

    @PostMapping
    public ResponseEntity<FacultadDTO> crear(@RequestBody FacultadDTO facultadDTO) {
        FacultadDTO facultadGuardada = facultadService.guardarFacultad(facultadDTO);
        return ResponseEntity.ok(facultadGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultadDTO> actualizar(@PathVariable Integer id, @RequestBody FacultadDTO facultadDTO) {
        facultadDTO.setIdFacultad(id);
        FacultadDTO facultadActualizada = facultadService.guardarFacultad(facultadDTO);
        return ResponseEntity.ok(facultadActualizada);
    }
}
