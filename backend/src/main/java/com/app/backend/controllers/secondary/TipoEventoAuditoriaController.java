package com.app.backend.controllers.secondary;

import com.app.backend.dtos.secondary.TipoEventoAuditoriaDTO;
import com.app.backend.services.secondary.ITipoEventoAuditoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para TipoEventoAuditoria del sistema secundario (SGA).
 */
@RestController
@RequestMapping("/api/secondary/tipos-evento-auditoria")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class TipoEventoAuditoriaController {

    private final ITipoEventoAuditoriaService tipoEventoService;

    @GetMapping
    public ResponseEntity<List<TipoEventoAuditoriaDTO>> obtenerTodos() {
        return ResponseEntity.ok(tipoEventoService.obtenerTodosLosTipos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<TipoEventoAuditoriaDTO>> obtenerActivos() {
        return ResponseEntity.ok(tipoEventoService.obtenerTiposActivos());
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<TipoEventoAuditoriaDTO> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(tipoEventoService.obtenerPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<TipoEventoAuditoriaDTO> crear(@RequestBody TipoEventoAuditoriaDTO dto) {
        return ResponseEntity.ok(tipoEventoService.guardarTipo(dto));
    }
}

