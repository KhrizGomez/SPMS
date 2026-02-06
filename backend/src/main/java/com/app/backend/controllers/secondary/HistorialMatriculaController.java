package com.app.backend.controllers.secondary;

import com.app.backend.dtos.secondary.HistorialMatriculaDTO;
import com.app.backend.services.secondary.IHistorialMatriculaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para HistorialMatricula del sistema secundario (SGA).
 */
@RestController
@RequestMapping("/api/secondary/historial-matriculas")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class HistorialMatriculaController {

    private final IHistorialMatriculaService historialService;

    @GetMapping
    public ResponseEntity<List<HistorialMatriculaDTO>> obtenerTodos() {
        return ResponseEntity.ok(historialService.obtenerTodosLosHistoriales());
    }

    @GetMapping("/estudiante/{idEstudiante}")
    public ResponseEntity<List<HistorialMatriculaDTO>> obtenerPorEstudiante(@PathVariable Integer idEstudiante) {
        return ResponseEntity.ok(historialService.obtenerPorEstudiante(idEstudiante));
    }

    @GetMapping("/semestre/{idSemestre}")
    public ResponseEntity<List<HistorialMatriculaDTO>> obtenerPorSemestre(@PathVariable Integer idSemestre) {
        return ResponseEntity.ok(historialService.obtenerPorSemestre(idSemestre));
    }

    @GetMapping("/carrera/{idCarrera}")
    public ResponseEntity<List<HistorialMatriculaDTO>> obtenerPorCarrera(@PathVariable Integer idCarrera) {
        return ResponseEntity.ok(historialService.obtenerPorCarrera(idCarrera));
    }

    @PostMapping
    public ResponseEntity<HistorialMatriculaDTO> crear(@RequestBody HistorialMatriculaDTO dto) {
        return ResponseEntity.ok(historialService.guardarHistorial(dto));
    }
}

