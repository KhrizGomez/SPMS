package com.app.backend.controllers;

import com.app.backend.dtos.CareerDTO;
import com.app.backend.dtos.InfoCoordinatorDTO;
import com.app.backend.services.ICareerService;
import com.app.backend.services.IInfoCoordinatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/info-coordinators")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoCoordinatorController {
    private final IInfoCoordinatorService infoCoordinatorService;

    @GetMapping
    public ResponseEntity<List<InfoCoordinatorDTO>> getAll(){
        return ResponseEntity.ok(infoCoordinatorService.getAllInfoCoordinators());
    }

    @PostMapping
    public ResponseEntity<InfoCoordinatorDTO> create(@RequestBody InfoCoordinatorDTO infoCoordinatorDTO){
        InfoCoordinatorDTO savedInfoCoordinator = infoCoordinatorService.setInfoCoordinator(infoCoordinatorDTO);
        return ResponseEntity.ok(savedInfoCoordinator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoCoordinatorDTO> update(@PathVariable Integer id, @RequestBody InfoCoordinatorDTO infoCoordinatorDTO){
        infoCoordinatorDTO.setIdUsuario(id);
        InfoCoordinatorDTO updateInfoCoordinator = infoCoordinatorService.setInfoCoordinator(infoCoordinatorDTO);
        return ResponseEntity.ok(updateInfoCoordinator);
    }
}
