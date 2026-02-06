package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.UsuarioDTO;
import com.app.backend.entities.primary.Usuario;
import com.app.backend.entities.primary.UsuarioRol;
import com.app.backend.repositories.primary.IConfiguracionUsuarioRepository;
import com.app.backend.repositories.primary.ICredencialRepository;
import com.app.backend.repositories.primary.IUsuarioRepository;
import com.app.backend.services.primary.IUsuarioService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para Usuario del sistema primario.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta.
 */
@Service
@RequiredArgsConstructor
public class ImplUsuarioService implements IUsuarioService {
    private final IUsuarioRepository usuarioRepository;
    private final IConfiguracionUsuarioRepository configuracionRepository;
    private final ICredencialRepository credencialRepository;

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<UsuarioDTO> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public UsuarioDTO obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public UsuarioDTO obtenerUsuarioPorCedula(String cedula) {
        return usuarioRepository.findByCedula(cedula)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con cédula: " + cedula));
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public UsuarioDTO crearUsuario(@NonNull UsuarioDTO usuarioDTO) {
        Usuario entidad = new Usuario();
        copiarDtoAEntidad(usuarioDTO, entidad);
        entidad.setCreadoEn(new Date());
        entidad.setActualizadoEn(new Date());
        Usuario entidadGuardada = usuarioRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public UsuarioDTO actualizarUsuario(Integer id, @NonNull UsuarioDTO usuarioDTO) {
        Usuario entidad = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        copiarDtoAEntidad(usuarioDTO, entidad);
        entidad.setActualizadoEn(new Date());
        Usuario entidadGuardada = usuarioRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con ID: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioDTO convertirADto(@NonNull Usuario entidad) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setIdUsuario(entidad.getIdUsuario());
        dto.setCedula(entidad.getCedula());
        dto.setNombres(entidad.getNombres());
        dto.setApellidos(entidad.getApellidos());
        dto.setCorreoInstitucional(entidad.getCorreoInstitucional());
        dto.setCorreoPersonal(entidad.getCorreoPersonal());
        dto.setTelefono(entidad.getTelefono());
        dto.setEstaActivo(entidad.getEstaActivo());
        dto.setCorreoVerificado(entidad.getCorreoVerificado());
        dto.setTelefonoVerificado(entidad.getTelefonoVerificado());
        dto.setIdUsuarioSga(entidad.getIdUsuarioSga());
        dto.setImportadoDeSga(entidad.getImportadoDeSga());
        dto.setUltimoAcceso(entidad.getUltimoAcceso());
        dto.setCreadoEn(entidad.getCreadoEn());
        dto.setActualizadoEn(entidad.getActualizadoEn());

        if (entidad.getConfiguracionUsuario() != null) {
            dto.setIdConfiguracion(entidad.getConfiguracionUsuario().getIdConfiguracion());
        }

        if (entidad.getCredencial() != null) {
            dto.setIdCredencial(entidad.getCredencial().getIdCredencial());
        }

        // Obtener roles del usuario
        if (entidad.getUsuariosRoles() != null && !entidad.getUsuariosRoles().isEmpty()) {
            List<String> roles = entidad.getUsuariosRoles().stream()
                    .filter(ur -> ur.getRol() != null)
                    .map(ur -> ur.getRol().getNombreRol())
                    .collect(Collectors.toList());
            dto.setRoles(roles);
        }

        return dto;
    }

    private void copiarDtoAEntidad(@NonNull UsuarioDTO dto, @NonNull Usuario entidad) {
        entidad.setCedula(dto.getCedula());
        entidad.setNombres(dto.getNombres());
        entidad.setApellidos(dto.getApellidos());
        entidad.setCorreoInstitucional(dto.getCorreoInstitucional());
        entidad.setCorreoPersonal(dto.getCorreoPersonal());
        entidad.setTelefono(dto.getTelefono());
        entidad.setEstaActivo(dto.getEstaActivo() != null ? dto.getEstaActivo() : true);

        if (dto.getIdConfiguracion() != null) {
            configuracionRepository.findById(dto.getIdConfiguracion())
                    .ifPresent(entidad::setConfiguracionUsuario);
        }

        if (dto.getIdCredencial() != null) {
            credencialRepository.findById(dto.getIdCredencial())
                    .ifPresent(entidad::setCredencial);
        }
    }
}
