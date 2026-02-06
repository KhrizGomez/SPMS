package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.TipoTramiteDTO;
import com.app.backend.services.primary.ITipoTramiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar Tipos de Trámite del sistema principal.
 */
@RestController
@RequestMapping("/api/primary/tipos-tramite")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TipoTramiteController {

    private final ITipoTramiteService tipoTramiteService;

    @GetMapping
    public ResponseEntity<List<TipoTramiteDTO>> obtenerTodos() {
        return ResponseEntity.ok(tipoTramiteService.obtenerTodosLosTiposTramite());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<TipoTramiteDTO>> obtenerActivos() {
        return ResponseEntity.ok(tipoTramiteService.obtenerActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoTramiteDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoTramiteService.obtenerPorId(id));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<TipoTramiteDTO>> obtenerPorCategoria(@PathVariable Integer idCategoria) {
        return ResponseEntity.ok(tipoTramiteService.obtenerPorCategoria(idCategoria));
    }

    @PostMapping
    public ResponseEntity<TipoTramiteDTO> crear(@RequestBody TipoTramiteDTO dto) {
        return new ResponseEntity<>(tipoTramiteService.crearTipoTramite(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoTramiteDTO> actualizar(@PathVariable Integer id, @RequestBody TipoTramiteDTO dto) {
        return ResponseEntity.ok(tipoTramiteService.actualizarTipoTramite(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        tipoTramiteService.eliminarTipoTramite(id);
        return ResponseEntity.noContent().build();
    }
}

