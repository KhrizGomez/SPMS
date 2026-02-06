package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.ConfiguracionDTO;
import com.app.backend.services.primary.IConfiguracionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/primary/configuraciones")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ConfiguracionController {
    private final IConfiguracionService configuracionService;

    @GetMapping
    public ResponseEntity<List<ConfiguracionDTO>> obtenerTodas() {
        return ResponseEntity.ok(configuracionService.obtenerTodasLasConfiguraciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConfiguracionDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(configuracionService.obtenerConfiguracionPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConfiguracionDTO> crear(@RequestBody ConfiguracionDTO configuracionDTO) {
        ConfiguracionDTO configuracionGuardada = configuracionService.crearConfiguracion(configuracionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(configuracionGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConfiguracionDTO> actualizar(@PathVariable Integer id, @RequestBody ConfiguracionDTO configuracionDTO) {
        ConfiguracionDTO configuracionActualizada = configuracionService.actualizarConfiguracion(id, configuracionDTO);
        return ResponseEntity.ok(configuracionActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        configuracionService.eliminarConfiguracion(id);
        return ResponseEntity.noContent().build();
    }
}
