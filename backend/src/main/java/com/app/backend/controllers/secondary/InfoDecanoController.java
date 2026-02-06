package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.InfoDecanoDTO;
import com.app.backend.services.secondary.IInfoDecanoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/info-decanos")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoDecanoController {
    private final IInfoDecanoService infoDecanoService;

    @GetMapping
    public ResponseEntity<List<InfoDecanoDTO>> obtenerTodos() {
        return ResponseEntity.ok(infoDecanoService.obtenerTodosLosInfoDecanos());
    }

    @PostMapping
    public ResponseEntity<InfoDecanoDTO> crear(@RequestBody InfoDecanoDTO infoDecanoDTO) {
        InfoDecanoDTO infoDecanoGuardado = infoDecanoService.guardarInfoDecano(infoDecanoDTO);
        return ResponseEntity.ok(infoDecanoGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoDecanoDTO> actualizar(@PathVariable Integer id, @RequestBody InfoDecanoDTO infoDecanoDTO) {
        infoDecanoDTO.setIdUsuario(id);
        InfoDecanoDTO infoDecanoActualizado = infoDecanoService.guardarInfoDecano(infoDecanoDTO);
        return ResponseEntity.ok(infoDecanoActualizado);
    }
}
