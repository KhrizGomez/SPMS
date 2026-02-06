package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.SolicitudDTO;
import com.app.backend.services.primary.ISolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para gestionar Solicitudes del sistema principal.
 */
@RestController
@RequestMapping("/api/primary/solicitudes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SolicitudController {

    private final ISolicitudService solicitudService;

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> obtenerTodas() {
        return ResponseEntity.ok(solicitudService.obtenerTodasLasSolicitudes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> obtenerPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(solicitudService.obtenerPorId(id));
    }

    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<SolicitudDTO> obtenerPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(solicitudService.obtenerPorCodigo(codigo));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<SolicitudDTO>> obtenerPorUsuario(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(solicitudService.obtenerPorUsuario(idUsuario));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<SolicitudDTO>> obtenerPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(solicitudService.obtenerPorEstado(estado));
    }

    @GetMapping("/asignado/{idUsuario}")
    public ResponseEntity<List<SolicitudDTO>> obtenerPorAsignado(@PathVariable Integer idUsuario) {
        return ResponseEntity.ok(solicitudService.obtenerPorAsignado(idUsuario));
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> crear(@RequestBody SolicitudDTO dto) {
        return new ResponseEntity<>(solicitudService.crearSolicitud(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDTO> actualizar(@PathVariable Integer id, @RequestBody SolicitudDTO dto) {
        return ResponseEntity.ok(solicitudService.actualizarSolicitud(id, dto));
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<SolicitudDTO> cambiarEstado(@PathVariable Integer id, @RequestParam String nuevoEstado) {
        return ResponseEntity.ok(solicitudService.cambiarEstado(id, nuevoEstado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}

