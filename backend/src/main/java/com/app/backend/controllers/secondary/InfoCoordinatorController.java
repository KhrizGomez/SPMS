package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.InfoCoordinatorDTO;
import com.app.backend.services.secondary.IInfoCoordinatorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/info-coordinators")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoCoordinatorController {
    private final IInfoCoordinatorService infoCoordinatorService;

    @GetMapping
    public ResponseEntity<List<InfoCoordinatorDTO>> getAll() {
        return ResponseEntity.ok(infoCoordinatorService.getAllInfoCoordinators());
    }

    @PostMapping
    public ResponseEntity<InfoCoordinatorDTO> create(@RequestBody InfoCoordinatorDTO infoCoordinatorDTO) {
        InfoCoordinatorDTO savedInfoCoordinator = infoCoordinatorService.setInfoCoordinator(infoCoordinatorDTO);
        return ResponseEntity.ok(savedInfoCoordinator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoCoordinatorDTO> update(@PathVariable Integer id, @RequestBody InfoCoordinatorDTO infoCoordinatorDTO) {
        infoCoordinatorDTO.setIdUsuario(id);
        InfoCoordinatorDTO updateInfoCoordinator = infoCoordinatorService.setInfoCoordinator(infoCoordinatorDTO);
        return ResponseEntity.ok(updateInfoCoordinator);
    }
}
