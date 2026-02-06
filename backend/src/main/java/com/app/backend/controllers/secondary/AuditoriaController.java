package com.app.backend.controllers.secondary;

import com.app.backend.dtos.secondary.AuditoriaDTO;
import com.app.backend.services.secondary.IAuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;

/**
 * Controlador REST para Auditoria del sistema secundario (SGA).
 */
@RestController
@RequestMapping("/api/secondary/auditorias")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AuditoriaController {

    private final IAuditoriaService auditoriaService;

    @GetMapping
    public ResponseEntity<List<AuditoriaDTO>> obtenerTodas() {
        return ResponseEntity.ok(auditoriaService.obtenerTodasLasAuditorias());
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<AuditoriaDTO>> obtenerPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(auditoriaService.obtenerPorUsuario(idUsuario));
    }

    @GetMapping("/tipo/{idTipoEvento}")
    public ResponseEntity<List<AuditoriaDTO>> obtenerPorTipoEvento(@PathVariable Integer idTipoEvento) {
        return ResponseEntity.ok(auditoriaService.obtenerPorTipoEvento(idTipoEvento));
    }

    @GetMapping("/tabla/{tablaAfectada}")
    public ResponseEntity<List<AuditoriaDTO>> obtenerPorTabla(@PathVariable String tablaAfectada) {
        return ResponseEntity.ok(auditoriaService.obtenerPorTabla(tablaAfectada));
    }

    @GetMapping("/rango-fechas")
    public ResponseEntity<List<AuditoriaDTO>> obtenerPorRangoFechas(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaInicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaFin) {
        return ResponseEntity.ok(auditoriaService.obtenerPorRangoFechas(fechaInicio, fechaFin));
    }

    @PostMapping
    public ResponseEntity<AuditoriaDTO> crear(@RequestBody AuditoriaDTO dto) {
        return ResponseEntity.ok(auditoriaService.guardarAuditoria(dto));
    }
}

