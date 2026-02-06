package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.CredencialesDTO;
import com.app.backend.services.primary.ICredencialesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/primary/credenciales")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class CredencialesController {
    private final ICredencialesService credencialesService;

    @GetMapping
    public ResponseEntity<List<CredencialesDTO>> obtenerTodas() {
        return ResponseEntity.ok(credencialesService.obtenerTodasLasCredenciales());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredencialesDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(credencialesService.obtenerCredencialesPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<CredencialesDTO> obtenerPorIdUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(credencialesService.obtenerCredencialesPorIdUsuario(idUsuario));
    }

    @PostMapping
    public ResponseEntity<CredencialesDTO> crear(@RequestBody CredencialesDTO credencialesDTO) {
        CredencialesDTO credencialesGuardadas = credencialesService.crearCredenciales(credencialesDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(credencialesGuardadas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredencialesDTO> actualizar(@PathVariable Integer id, @RequestBody CredencialesDTO credencialesDTO) {
        CredencialesDTO credencialesActualizadas = credencialesService.actualizarCredenciales(id, credencialesDTO);
        return ResponseEntity.ok(credencialesActualizadas);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        credencialesService.eliminarCredenciales(id);
        return ResponseEntity.noContent().build();
    }
}
