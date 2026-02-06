package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.CarreraDTO;
import com.app.backend.services.secondary.ICarreraService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/carreras")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CarreraController {
    private final ICarreraService carreraService;

    @GetMapping
    public ResponseEntity<List<CarreraDTO>> obtenerTodas() {
        return ResponseEntity.ok(carreraService.obtenerTodasLasCarreras());
    }

    @PostMapping
    public ResponseEntity<CarreraDTO> crear(@RequestBody CarreraDTO carreraDTO) {
        CarreraDTO carreraGuardada = carreraService.guardarCarrera(carreraDTO);
        return ResponseEntity.ok(carreraGuardada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarreraDTO> actualizar(@PathVariable Integer id, @RequestBody CarreraDTO carreraDTO) {
        carreraDTO.setIdCarrera(id);
        CarreraDTO carreraActualizada = carreraService.guardarCarrera(carreraDTO);
        return ResponseEntity.ok(carreraActualizada);
    }
}
