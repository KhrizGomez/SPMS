package com.app.backend.controllers.primary;

import com.app.backend.dtos.primary.RegistroUsuarioInternoDTO;
import com.app.backend.dtos.primary.RespuestaRegistroUsuarioDTO;
import com.app.backend.services.primary.IRegistroUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST para el registro de usuarios.
 * Maneja las solicitudes de registro de usuarios internos y externos.
 */
@RestController
@RequestMapping("/api/primary/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class RegistroUsuarioController {

    private final IRegistroUsuarioService registroUsuarioService;

    /**
     * Endpoint para registrar un usuario interno (UTEQ).
     * Busca los datos en la BD secundaria y crea el usuario en la BD primaria.
     *
     * POST /api/primary/auth/registro/interno
     * Body: { "cedula": "1234567890" }
     *
     * @param registroDTO DTO con la cédula del usuario
     * @return Respuesta con los datos del usuario y credenciales generadas
     */
    @PostMapping("/registro/interno")
    public ResponseEntity<RespuestaRegistroUsuarioDTO> registrarUsuarioInterno(
            @RequestBody RegistroUsuarioInternoDTO registroDTO) {

        RespuestaRegistroUsuarioDTO respuesta = registroUsuarioService.registrarUsuarioInterno(registroDTO);

        if (respuesta.isExitoso()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(respuesta);
        }
    }

    /**
     * Endpoint para verificar si un usuario existe en el sistema secundario (institucional).
     * Útil para validar antes de registrar.
     *
     * GET /api/primary/auth/verificar/interno/{cedula}
     *
     * @param cedula Cédula del usuario
     * @return true si el usuario existe en el sistema institucional
     */
    @GetMapping("/verificar/interno/{cedula}")
    public ResponseEntity<Boolean> verificarUsuarioInterno(@PathVariable String cedula) {
        boolean existe = registroUsuarioService.existeUsuarioInternoPorCedula(cedula);
        return ResponseEntity.ok(existe);
    }

    /**
     * Endpoint para verificar si un usuario ya está registrado en el sistema.
     *
     * GET /api/primary/auth/verificar/registrado/{cedula}
     *
     * @param cedula Cédula del usuario
     * @return true si el usuario ya está registrado
     */
    @GetMapping("/verificar/registrado/{cedula}")
    public ResponseEntity<Boolean> verificarUsuarioRegistrado(@PathVariable String cedula) {
        boolean existe = registroUsuarioService.existeUsuarioPorCedula(cedula);
        return ResponseEntity.ok(existe);
    }
}
