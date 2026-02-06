package com.app.backend.controllers.secondary;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.backend.dtos.secondary.UsuarioSecundarioDTO;
import com.app.backend.services.secondary.IUsuarioSecundarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/secondary/usuarios")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UsuarioSecundarioController {
    private final IUsuarioSecundarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioSecundarioDTO>> obtenerTodos() {
        return ResponseEntity.ok(usuarioService.obtenerTodosLosUsuarios());
    }

    @PostMapping
    public ResponseEntity<UsuarioSecundarioDTO> crear(@RequestBody UsuarioSecundarioDTO usuarioDTO) {
        UsuarioSecundarioDTO usuarioGuardado = usuarioService.guardarUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioGuardado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioSecundarioDTO> actualizar(@PathVariable Integer id, @RequestBody UsuarioSecundarioDTO usuarioDTO) {
        usuarioDTO.setIdUsuario(id);
        UsuarioSecundarioDTO usuarioActualizado = usuarioService.guardarUsuario(usuarioDTO);
        return ResponseEntity.ok(usuarioActualizado);
    }
}
