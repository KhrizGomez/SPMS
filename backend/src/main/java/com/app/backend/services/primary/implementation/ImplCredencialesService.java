package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.CredencialesDTO;
import com.app.backend.entities.primary.Credenciales;
import com.app.backend.repositories.primary.ICredencialesRepository;
import com.app.backend.repositories.primary.IUsuarioRepository;
import com.app.backend.services.primary.ICredencialesService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para Credenciales del sistema primario.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta.
 */
@Service
@RequiredArgsConstructor
public class ImplCredencialesService implements ICredencialesService {
    private final ICredencialesRepository credencialesRepository;
    private final IUsuarioRepository usuarioRepository;

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<CredencialesDTO> obtenerTodasLasCredenciales() {
        return credencialesRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public CredencialesDTO obtenerCredencialesPorId(Integer id) {
        return credencialesRepository.findById(id)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Credenciales no encontradas con ID: " + id));
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public CredencialesDTO obtenerCredencialesPorIdUsuario(Integer idUsuario) {
        return credencialesRepository.findByUsuario_IdUsuario(idUsuario)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Credenciales no encontradas para el usuario: " + idUsuario));
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public CredencialesDTO crearCredenciales(@NonNull CredencialesDTO credencialesDTO) {
        Credenciales entidad = new Credenciales();
        copiarDtoAEntidad(credencialesDTO, entidad);
        entidad.setFechaModificacion(new Date());
        Credenciales entidadGuardada = credencialesRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public CredencialesDTO actualizarCredenciales(Integer id, @NonNull CredencialesDTO credencialesDTO) {
        Credenciales entidad = credencialesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Credenciales no encontradas con ID: " + id));
        copiarDtoAEntidad(credencialesDTO, entidad);
        entidad.setFechaModificacion(new Date());
        Credenciales entidadGuardada = credencialesRepository.save(entidad);
        return convertirADto(entidadGuardada);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public void eliminarCredenciales(Integer id) {
        if (!credencialesRepository.existsById(id)) {
            throw new RuntimeException("Credenciales no encontradas con ID: " + id);
        }
        credencialesRepository.deleteById(id);
    }

    private CredencialesDTO convertirADto(@NonNull Credenciales entidad) {
        CredencialesDTO dto = new CredencialesDTO();
        dto.setIdCredenciales(entidad.getIdCredenciales());
        dto.setContrasena(entidad.getContrasena());
        dto.setFechaModificacion(entidad.getFechaModificacion());
        if (entidad.getUsuario() != null) {
            dto.setIdUsuario(entidad.getUsuario().getIdUsuario());
        }
        return dto;
    }

    private void copiarDtoAEntidad(@NonNull CredencialesDTO dto, @NonNull Credenciales entidad) {
        entidad.setContrasena(dto.getContrasena());
        if (dto.getIdUsuario() != null) {
            usuarioRepository.findById(dto.getIdUsuario())
                    .ifPresent(entidad::setUsuario);
        }
    }
}
