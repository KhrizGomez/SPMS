package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.TipoTramiteDTO;
import com.app.backend.entities.primary.TipoTramite;
import com.app.backend.repositories.primary.ICategoriaTramiteRepository;
import com.app.backend.repositories.primary.ITipoTramiteRepository;
import com.app.backend.services.primary.ITipoTramiteService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para TipoTramite del sistema principal.
 */
@Service
@RequiredArgsConstructor
public class ImplTipoTramiteService implements ITipoTramiteService {

    private final ITipoTramiteRepository tipoTramiteRepository;
    private final ICategoriaTramiteRepository categoriaRepository;

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<TipoTramiteDTO> obtenerTodosLosTiposTramite() {
        return tipoTramiteRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<TipoTramiteDTO> obtenerActivos() {
        return tipoTramiteRepository.findByEstaActivoTrue().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<TipoTramiteDTO> obtenerPorCategoria(Integer idCategoria) {
        return tipoTramiteRepository.findByCategoria_IdCategoria(idCategoria).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public TipoTramiteDTO obtenerPorId(Integer id) {
        return tipoTramiteRepository.findById(id)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Tipo de trámite no encontrado: " + id));
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public TipoTramiteDTO crearTipoTramite(@NonNull TipoTramiteDTO dto) {
        TipoTramite entidad = new TipoTramite();

        entidad.setNombreTramite(dto.getNombreTramite());
        entidad.setDescripcionTramite(dto.getDescripcionTramite());
        entidad.setDiasEstimados(dto.getDiasEstimados() != null ? dto.getDiasEstimados() : 5);
        entidad.setRequierePago(dto.getRequierePago() != null ? dto.getRequierePago() : false);
        entidad.setMontoPago(dto.getMontoPago());
        entidad.setEstaActivo(true);
        entidad.setDisponibleExternos(dto.getDisponibleExternos() != null ? dto.getDisponibleExternos() : false);
        entidad.setCreadoEn(new Date());
        entidad.setActualizadoEn(new Date());

        if (dto.getIdCategoria() != null) {
            categoriaRepository.findById(dto.getIdCategoria())
                    .ifPresent(entidad::setCategoria);
        }

        TipoTramite guardado = tipoTramiteRepository.save(entidad);
        return convertirADto(guardado);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public TipoTramiteDTO actualizarTipoTramite(Integer id, @NonNull TipoTramiteDTO dto) {
        TipoTramite entidad = tipoTramiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de trámite no encontrado: " + id));

        entidad.setNombreTramite(dto.getNombreTramite());
        entidad.setDescripcionTramite(dto.getDescripcionTramite());
        entidad.setDiasEstimados(dto.getDiasEstimados());
        entidad.setRequierePago(dto.getRequierePago());
        entidad.setMontoPago(dto.getMontoPago());
        entidad.setEstaActivo(dto.getEstaActivo());
        entidad.setDisponibleExternos(dto.getDisponibleExternos());
        entidad.setActualizadoEn(new Date());

        if (dto.getIdCategoria() != null) {
            categoriaRepository.findById(dto.getIdCategoria())
                    .ifPresent(entidad::setCategoria);
        }

        TipoTramite actualizado = tipoTramiteRepository.save(entidad);
        return convertirADto(actualizado);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public void eliminarTipoTramite(Integer id) {
        if (!tipoTramiteRepository.existsById(id)) {
            throw new RuntimeException("Tipo de trámite no encontrado: " + id);
        }
        tipoTramiteRepository.deleteById(id);
    }

    private TipoTramiteDTO convertirADto(@NonNull TipoTramite entidad) {
        TipoTramiteDTO dto = new TipoTramiteDTO();
        dto.setIdTipoTramite(entidad.getIdTipoTramite());
        dto.setNombreTramite(entidad.getNombreTramite());
        dto.setDescripcionTramite(entidad.getDescripcionTramite());

        if (entidad.getCategoria() != null) {
            dto.setIdCategoria(entidad.getCategoria().getIdCategoria());
            dto.setNombreCategoria(entidad.getCategoria().getNombreCategoria());
        }

        if (entidad.getFlujo() != null) {
            dto.setIdFlujo(entidad.getFlujo().getIdFlujo());
            dto.setNombreFlujo(entidad.getFlujo().getNombreFlujo());
        }

        dto.setDiasEstimados(entidad.getDiasEstimados());
        dto.setRequierePago(entidad.getRequierePago());
        dto.setMontoPago(entidad.getMontoPago());
        dto.setEstaActivo(entidad.getEstaActivo());
        dto.setDisponibleExternos(entidad.getDisponibleExternos());
        dto.setCreadoEn(entidad.getCreadoEn());
        dto.setActualizadoEn(entidad.getActualizadoEn());

        return dto;
    }
}

