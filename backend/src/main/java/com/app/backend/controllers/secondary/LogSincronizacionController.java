package com.app.backend.controllers.secondary;

import com.app.backend.dtos.secondary.LogSincronizacionDTO;
import com.app.backend.services.secondary.ILogSincronizacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para LogSincronizacion del sistema secundario (SGA).
 */
@RestController
@RequestMapping("/api/secondary/log-sincronizacion")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LogSincronizacionController {

    private final ILogSincronizacionService logService;

    @GetMapping
    public ResponseEntity<List<LogSincronizacionDTO>> obtenerTodos() {
        return ResponseEntity.ok(logService.obtenerTodosLosLogs());
    }

    @GetMapping("/sistema/{sistemaDestino}")
    public ResponseEntity<List<LogSincronizacionDTO>> obtenerPorSistema(@PathVariable String sistemaDestino) {
        return ResponseEntity.ok(logService.obtenerPorSistemaDestino(sistemaDestino));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<LogSincronizacionDTO>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(logService.obtenerPorEstado(estado));
    }

    @GetMapping("/entidad/{tipoEntidad}")
    public ResponseEntity<List<LogSincronizacionDTO>> obtenerPorTipoEntidad(@PathVariable String tipoEntidad) {
        return ResponseEntity.ok(logService.obtenerPorTipoEntidad(tipoEntidad));
    }

    @PostMapping
    public ResponseEntity<LogSincronizacionDTO> crear(@RequestBody LogSincronizacionDTO dto) {
        return ResponseEntity.ok(logService.guardarLog(dto));
    }

    @PatchMapping("/{idSync}/estado")
    public ResponseEntity<LogSincronizacionDTO> actualizarEstado(
            @PathVariable Integer idSync,
            @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(logService.actualizarEstado(idSync, nuevoEstado));
    }
}

