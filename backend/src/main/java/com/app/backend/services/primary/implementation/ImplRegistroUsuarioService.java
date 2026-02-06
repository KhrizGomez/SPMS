package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.RegistroUsuarioInternoDTO;
import com.app.backend.dtos.primary.RespuestaRegistroUsuarioDTO;
import com.app.backend.entities.primary.Credenciales;
import com.app.backend.entities.primary.Usuario;
import com.app.backend.repositories.primary.ICredencialesRepository;
import com.app.backend.repositories.primary.IUsuarioRepository;
import com.app.backend.repositories.secondary.IUsuarioSecundarioRepository;
import com.app.backend.services.primary.IRegistroUsuarioService;
import com.app.backend.services.utils.GeneradorCredencialesService;
import com.app.backend.services.utils.CorreoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

/**
 * Implementación del servicio de registro de usuarios.
 * Maneja el registro de usuarios internos (UTEQ) buscando sus datos en la base secundaria.
 */
@Service
@RequiredArgsConstructor
public class ImplRegistroUsuarioService implements IRegistroUsuarioService {

    // Repositorio de usuarios del sistema primario (donde se crean las cuentas)
    private final IUsuarioRepository usuarioRepository;

    // Repositorio de credenciales del sistema primario
    private final ICredencialesRepository credencialesRepository;

    // Repositorio de usuarios del sistema secundario (datos institucionales)
    private final IUsuarioSecundarioRepository usuarioSecundarioRepository;

    // Servicio para generar credenciales aleatorias
    private final GeneradorCredencialesService generadorCredenciales;

    // Servicio para enviar correos
    private final CorreoService correoService;

    /**
     * Registra un usuario interno buscando sus datos en el sistema secundario.
     *
     * Flujo:
     * 1. Buscar usuario en BD secundaria por cédula
     * 2. Verificar que no exista ya en BD primaria
     * 3. Crear usuario en BD primaria con datos de BD secundaria
     * 4. Generar usuario y contraseña aleatorios
     * 5. Guardar credenciales
     * 6. Enviar correo con las credenciales
     */
    @Override
    @Transactional
    public RespuestaRegistroUsuarioDTO registrarUsuarioInterno(RegistroUsuarioInternoDTO registroDTO) {
        RespuestaRegistroUsuarioDTO respuesta = new RespuestaRegistroUsuarioDTO();

        try {
            // 1. Validar que se proporcionó la cédula
            if (registroDTO.getCedula() == null || registroDTO.getCedula().trim().isEmpty()) {
                respuesta.setExitoso(false);
                respuesta.setMensaje("La cédula es requerida");
                return respuesta;
            }

            String cedula = registroDTO.getCedula().trim();

            // 2. Verificar si el usuario ya existe en el sistema primario
            if (existeUsuarioPorCedula(cedula)) {
                respuesta.setExitoso(false);
                respuesta.setMensaje("El usuario ya está registrado en el sistema");
                return respuesta;
            }

            // 3. Buscar usuario en el sistema secundario (datos institucionales)
            Optional<com.app.backend.entities.secondary.Usuario> usuarioSecundarioOpt =
                usuarioSecundarioRepository.findByCedula(cedula);

            if (usuarioSecundarioOpt.isEmpty()) {
                respuesta.setExitoso(false);
                respuesta.setMensaje("No se encontró un usuario con esa cédula en el sistema institucional. " +
                                   "Verifique que la cédula sea correcta o regístrese como usuario externo.");
                return respuesta;
            }

            // 4. Obtener datos del usuario secundario
            com.app.backend.entities.secondary.Usuario usuarioSecundario = usuarioSecundarioOpt.get();

            // 5. Crear usuario en el sistema primario
            Usuario usuarioPrimario = new Usuario();
            usuarioPrimario.setCedula(cedula);
            usuarioPrimario.setNombres(usuarioSecundario.getNombres());
            usuarioPrimario.setApellidos(usuarioSecundario.getApellidos());
            usuarioPrimario.setCorreoInstitucional(usuarioSecundario.getCorreoInstitucional());
            usuarioPrimario.setCorreoPersonal(usuarioSecundario.getCorreoPersonal());
            usuarioPrimario.setTelefono(usuarioSecundario.getTelefono());
            usuarioPrimario.setEstaActivo(false); // Usuario inactivo hasta que admin apruebe
            usuarioPrimario.setIdUsuarioSga(usuarioSecundario.getIdUsuario());
            usuarioPrimario.setImportadoDeSga(true);
            usuarioPrimario.setFechaImportacion(new Date());
            usuarioPrimario.setCreadoEn(new Date());
            usuarioPrimario.setActualizadoEn(new Date());

            // 6. Guardar usuario primario
            Usuario usuarioGuardado = usuarioRepository.save(usuarioPrimario);

            // 7. Generar credenciales aleatorias
            String usuarioGenerado = generadorCredenciales.generarNombreUsuario(
                usuarioGuardado.getNombres(),
                usuarioGuardado.getApellidos()
            );
            String contrasenaGenerada = generadorCredenciales.generarContrasena();

            // 8. Crear y guardar credenciales
            Credenciales credenciales = new Credenciales();
            credenciales.setUsuario(usuarioGuardado);
            credenciales.setContrasena(contrasenaGenerada); // TODO: Encriptar con BCrypt
            credenciales.setFechaModificacion(new Date());
            credencialesRepository.save(credenciales);

            // 9. Enviar correo con las credenciales
            String correoDestino = usuarioGuardado.getCorreoInstitucional() != null
                ? usuarioGuardado.getCorreoInstitucional()
                : usuarioGuardado.getCorreoPersonal();

            correoService.enviarCorreoCredenciales(
                correoDestino,
                usuarioGuardado.getNombres(),
                usuarioGenerado,
                contrasenaGenerada
            );

            // Determinar el rol desde el sistema secundario
            String rolUsuario = usuarioSecundario.getRol() != null ? usuarioSecundario.getRol() : "estudiante";

            // 10. Construir respuesta exitosa
            respuesta.setExitoso(true);
            respuesta.setMensaje("Usuario registrado exitosamente. Las credenciales han sido enviadas a su correo.");
            respuesta.setIdUsuario(usuarioGuardado.getIdUsuario());
            respuesta.setNombres(usuarioGuardado.getNombres());
            respuesta.setApellidos(usuarioGuardado.getApellidos());
            respuesta.setCedula(usuarioGuardado.getCedula());
            respuesta.setCorreoInstitucional(usuarioGuardado.getCorreoInstitucional());
            respuesta.setCorreoPersonal(usuarioGuardado.getCorreoPersonal());
            respuesta.setRol(rolUsuario);
            respuesta.setUsuarioGenerado(usuarioGenerado);
            respuesta.setContrasenaGenerada(contrasenaGenerada); // Solo para mostrar una vez

            return respuesta;

        } catch (Exception e) {
            respuesta.setExitoso(false);
            respuesta.setMensaje("Error al registrar el usuario: " + e.getMessage());
            return respuesta;
        }
    }

    /**
     * Verifica si un usuario ya existe en el sistema primario.
     */
    @Override
    public boolean existeUsuarioPorCedula(String cedula) {
        return usuarioRepository.findByCedula(cedula).isPresent();
    }

    /**
     * Verifica si un usuario existe en el sistema secundario.
     */
    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public boolean existeUsuarioInternoPorCedula(String cedula) {
        return usuarioSecundarioRepository.findByCedula(cedula).isPresent();
    }
}
