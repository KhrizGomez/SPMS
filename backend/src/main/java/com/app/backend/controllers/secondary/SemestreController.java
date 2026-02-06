package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.SemestreDTO;
import com.app.backend.services.secondary.ISemestreService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/semestres")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class SemestreController {
    private final ISemestreService semestreService;

    @GetMapping
    public ResponseEntity<List<SemestreDTO>> obtenerTodos() {
        return ResponseEntity.ok(semestreService.obtenerTodosLosSemestres());
    }

    @PostMapping
    public ResponseEntity<SemestreDTO> crear(@RequestBody SemestreDTO semestreDTO) {
        SemestreDTO semestreGuardado = semestreService.guardarSemestre(semestreDTO);
        return ResponseEntity.ok(semestreGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemestreDTO> actualizar(@PathVariable Integer id, @RequestBody SemestreDTO semestreDTO) {
        semestreDTO.setIdSemestre(id);
        SemestreDTO semestreActualizado = semestreService.guardarSemestre(semestreDTO);
        return ResponseEntity.ok(semestreActualizado);
    }
}
