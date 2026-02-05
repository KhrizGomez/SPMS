package com.app.backend.controllers;


import com.app.backend.dtos.InfoCoordinatorDTO;
import com.app.backend.dtos.InfoDeanDTO;
import com.app.backend.services.IInfoCoordinatorService;
import com.app.backend.services.IInfoDeanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/info-deans")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoDeanController {
    private final IInfoDeanService infoDeanService;

    @GetMapping
    public ResponseEntity<List<InfoDeanDTO>> getAll(){
        return ResponseEntity.ok(infoDeanService.getAllInfoDeans());
    }

    @PostMapping
    public ResponseEntity<InfoDeanDTO> create(@RequestBody InfoDeanDTO infoDeanDTO){
        InfoDeanDTO savedInfoDean = infoDeanService.setInfoDean(infoDeanDTO);
        return ResponseEntity.ok(savedInfoDean);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoDeanDTO> update(@PathVariable Integer id, @RequestBody InfoDeanDTO infoDeanDTO){
        infoDeanDTO.setIdUsuario(id);
        InfoDeanDTO updateInfoDean = infoDeanService.setInfoDean(infoDeanDTO);
        return ResponseEntity.ok(updateInfoDean);
    }
}
