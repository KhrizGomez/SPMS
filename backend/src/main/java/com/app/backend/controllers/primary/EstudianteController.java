package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.EstudianteDTO;
import com.app.backend.services.primary.IEstudianteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar Estudiantes del sistema principal.
 */
@RestController
@RequestMapping("/api/primary/estudiantes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EstudianteController {

    private final IEstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> obtenerTodos() {
        return ResponseEntity.ok(estudianteService.obtenerTodosLosEstudiantes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(estudianteService.obtenerPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<EstudianteDTO> obtenerPorIdUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(estudianteService.obtenerPorIdUsuario(idUsuario));
    }

    @GetMapping("/carrera/{idCarrera}")
    public ResponseEntity<List<EstudianteDTO>> obtenerPorCarrera(@PathVariable Integer idCarrera) {
        return ResponseEntity.ok(estudianteService.obtenerPorCarrera(idCarrera));
    }

    @PostMapping
    public ResponseEntity<EstudianteDTO> crear(@RequestBody EstudianteDTO dto) {
        return new ResponseEntity<>(estudianteService.crearEstudiante(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDTO> actualizar(@PathVariable Integer id, @RequestBody EstudianteDTO dto) {
        return ResponseEntity.ok(estudianteService.actualizarEstudiante(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        estudianteService.eliminarEstudiante(id);
        return ResponseEntity.noContent().build();
    }
}

