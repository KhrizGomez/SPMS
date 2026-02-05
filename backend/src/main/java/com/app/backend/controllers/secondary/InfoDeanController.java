package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.InfoDeanDTO;
import com.app.backend.services.secondary.IInfoDeanService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/info-deans")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class InfoDeanController {
    private final IInfoDeanService infoDeanService;

    @GetMapping
    public ResponseEntity<List<InfoDeanDTO>> getAll() {
        return ResponseEntity.ok(infoDeanService.getAllInfoDeans());
    }

    @PostMapping
    public ResponseEntity<InfoDeanDTO> create(@RequestBody InfoDeanDTO infoDeanDTO) {
        InfoDeanDTO savedInfoDean = infoDeanService.setInfoDean(infoDeanDTO);
        return ResponseEntity.ok(savedInfoDean);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InfoDeanDTO> update(@PathVariable Integer id, @RequestBody InfoDeanDTO infoDeanDTO) {
        infoDeanDTO.setIdUsuario(id);
        InfoDeanDTO updateInfoDean = infoDeanService.setInfoDean(infoDeanDTO);
        return ResponseEntity.ok(updateInfoDean);
    }
}
