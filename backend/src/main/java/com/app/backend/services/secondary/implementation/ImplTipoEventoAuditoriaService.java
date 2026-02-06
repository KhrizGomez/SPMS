package com.app.backend.services.secondary.implementation;

import com.app.backend.dtos.secondary.TipoEventoAuditoriaDTO;
import com.app.backend.entities.secondary.TipoEventoAuditoria;
import com.app.backend.repositories.secondary.ITipoEventoAuditoriaRepository;
import com.app.backend.services.secondary.ITipoEventoAuditoriaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para TipoEventoAuditoria.
 */
@Service
@RequiredArgsConstructor
public class ImplTipoEventoAuditoriaService implements ITipoEventoAuditoriaService {

    private final ITipoEventoAuditoriaRepository repository;

    @Override
    public List<TipoEventoAuditoriaDTO> obtenerTodosLosTipos() {
        return repository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TipoEventoAuditoriaDTO> obtenerTiposActivos() {
        return repository.findByEstaActivoTrue().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    public TipoEventoAuditoriaDTO obtenerPorCodigo(String codigoEvento) {
        return repository.findByCodigoEvento(codigoEvento)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Tipo de evento no encontrado: " + codigoEvento));
    }

    @Override
    public TipoEventoAuditoriaDTO guardarTipo(@NonNull TipoEventoAuditoriaDTO dto) {
        TipoEventoAuditoria entidad = new TipoEventoAuditoria();
        entidad.setCodigoEvento(dto.getCodigoEvento());
        entidad.setNombreEvento(dto.getNombreEvento());
        entidad.setDescripcionEvento(dto.getDescripcionEvento());
        entidad.setSeveridad(dto.getSeveridad());
        entidad.setEstaActivo(dto.getEstaActivo());

        TipoEventoAuditoria guardada = repository.save(entidad);
        return convertirADto(guardada);
    }

    private TipoEventoAuditoriaDTO convertirADto(@NonNull TipoEventoAuditoria entidad) {
        TipoEventoAuditoriaDTO dto = new TipoEventoAuditoriaDTO();
        dto.setIdTipoEvento(entidad.getIdTipoEvento());
        dto.setCodigoEvento(entidad.getCodigoEvento());
        dto.setNombreEvento(entidad.getNombreEvento());
        dto.setDescripcionEvento(entidad.getDescripcionEvento());
        dto.setSeveridad(entidad.getSeveridad());
        dto.setEstaActivo(entidad.getEstaActivo());
        return dto;
    }
}

