package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.InfoEstudianteDTO;
import com.app.backend.services.secondary.IInfoEstudianteService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/info-estudiantes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoEstudianteController {
    private final IInfoEstudianteService infoEstudianteService;

    @GetMapping
    public ResponseEntity<List<InfoEstudianteDTO>> obtenerTodos() {
        return ResponseEntity.ok(infoEstudianteService.obtenerTodosLosInfoEstudiantes());
    }

    @PostMapping
    public ResponseEntity<InfoEstudianteDTO> crear(@RequestBody InfoEstudianteDTO infoEstudianteDTO) {
        InfoEstudianteDTO infoEstudianteGuardado = infoEstudianteService.guardarInfoEstudiante(infoEstudianteDTO);
        return ResponseEntity.ok(infoEstudianteGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoEstudianteDTO> actualizar(@PathVariable Integer id, @RequestBody InfoEstudianteDTO infoEstudianteDTO) {
        infoEstudianteDTO.setIdUsuario(id);
        InfoEstudianteDTO infoEstudianteActualizado = infoEstudianteService.guardarInfoEstudiante(infoEstudianteDTO);
        return ResponseEntity.ok(infoEstudianteActualizado);
    }
}
