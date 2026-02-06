package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.InfoCoordinadorDTO;
import com.app.backend.services.secondary.IInfoCoordinadorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/info-coordinadores")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoCoordinadorController {
    private final IInfoCoordinadorService infoCoordinadorService;

    @GetMapping
    public ResponseEntity<List<InfoCoordinadorDTO>> obtenerTodos() {
        return ResponseEntity.ok(infoCoordinadorService.obtenerTodosLosInfoCoordinadores());
    }

    @PostMapping
    public ResponseEntity<InfoCoordinadorDTO> crear(@RequestBody InfoCoordinadorDTO infoCoordinadorDTO) {
        InfoCoordinadorDTO infoCoordinadorGuardado = infoCoordinadorService.guardarInfoCoordinador(infoCoordinadorDTO);
        return ResponseEntity.ok(infoCoordinadorGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoCoordinadorDTO> actualizar(@PathVariable Integer id, @RequestBody InfoCoordinadorDTO infoCoordinadorDTO) {
        infoCoordinadorDTO.setIdUsuario(id);
        InfoCoordinadorDTO infoCoordinadorActualizado = infoCoordinadorService.guardarInfoCoordinador(infoCoordinadorDTO);
        return ResponseEntity.ok(infoCoordinadorActualizado);
    }
}
