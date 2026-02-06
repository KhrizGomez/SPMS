package com.app.backend.services.primary.implementation;

import com.app.backend.dtos.primary.SolicitudDTO;
import com.app.backend.entities.primary.Solicitud;
import com.app.backend.repositories.primary.*;
import com.app.backend.services.primary.ISolicitudService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para Solicitud del sistema principal.
 */
@Service
@RequiredArgsConstructor
public class ImplSolicitudService implements ISolicitudService {

    private final ISolicitudRepository solicitudRepository;
    private final ITipoTramiteRepository tipoTramiteRepository;
    private final IUsuarioRepository usuarioRepository;
    private final ICarreraRepository carreraRepository;
    private final IFacultadRepository facultadRepository;

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<SolicitudDTO> obtenerTodasLasSolicitudes() {
        return solicitudRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public SolicitudDTO obtenerPorId(Integer id) {
        return solicitudRepository.findById(id)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada: " + id));
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public SolicitudDTO obtenerPorCodigo(String codigoSolicitud) {
        return solicitudRepository.findByCodigoSolicitud(codigoSolicitud)
                .map(this::convertirADto)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada: " + codigoSolicitud));
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<SolicitudDTO> obtenerPorUsuario(Integer idUsuario) {
        return solicitudRepository.findByUsuario_IdUsuario(idUsuario).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<SolicitudDTO> obtenerPorEstado(String estado) {
        return solicitudRepository.findByEstadoActual(estado).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager", readOnly = true)
    public List<SolicitudDTO> obtenerPorAsignado(Integer idUsuario) {
        return solicitudRepository.findByAsignadoA_IdUsuario(idUsuario).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public SolicitudDTO crearSolicitud(@NonNull SolicitudDTO dto) {
        Solicitud entidad = new Solicitud();

        if (dto.getIdTipoTramite() != null) {
            tipoTramiteRepository.findById(dto.getIdTipoTramite())
                    .ifPresent(entidad::setTipoTramite);
        }

        if (dto.getIdUsuario() != null) {
            usuarioRepository.findById(dto.getIdUsuario())
                    .ifPresent(entidad::setUsuario);
        }

        if (dto.getIdCarrera() != null) {
            carreraRepository.findById(dto.getIdCarrera())
                    .ifPresent(entidad::setCarrera);
        }

        if (dto.getIdFacultad() != null) {
            facultadRepository.findById(dto.getIdFacultad())
                    .ifPresent(entidad::setFacultad);
        }

        entidad.setDetallesSolicitud(dto.getDetallesSolicitud());
        entidad.setPrioridad(dto.getPrioridad() != null ? dto.getPrioridad() : "normal");
        entidad.setEstadoActual("pendiente");
        entidad.setCreadoEn(new Date());
        entidad.setActualizadoEn(new Date());

        Solicitud guardada = solicitudRepository.save(entidad);
        return convertirADto(guardada);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public SolicitudDTO actualizarSolicitud(Integer id, @NonNull SolicitudDTO dto) {
        Solicitud entidad = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada: " + id));

        entidad.setDetallesSolicitud(dto.getDetallesSolicitud());
        entidad.setPrioridad(dto.getPrioridad());
        entidad.setResolucion(dto.getResolucion());
        entidad.setActualizadoEn(new Date());

        if (dto.getIdAsignadoA() != null) {
            usuarioRepository.findById(dto.getIdAsignadoA())
                    .ifPresent(entidad::setAsignadoA);
        }

        Solicitud actualizada = solicitudRepository.save(entidad);
        return convertirADto(actualizada);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public SolicitudDTO cambiarEstado(Integer id, String nuevoEstado) {
        Solicitud entidad = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada: " + id));

        entidad.setEstadoActual(nuevoEstado);
        entidad.setActualizadoEn(new Date());

        if ("aprobado".equalsIgnoreCase(nuevoEstado) || "rechazado".equalsIgnoreCase(nuevoEstado)) {
            entidad.setFechaRealFin(new Date());
        }

        Solicitud actualizada = solicitudRepository.save(entidad);
        return convertirADto(actualizada);
    }

    @Override
    @Transactional(value = "primaryTransactionManager")
    public void eliminarSolicitud(Integer id) {
        if (!solicitudRepository.existsById(id)) {
            throw new RuntimeException("Solicitud no encontrada: " + id);
        }
        solicitudRepository.deleteById(id);
    }

    private SolicitudDTO convertirADto(@NonNull Solicitud entidad) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setIdSolicitud(entidad.getIdSolicitud());
        dto.setCodigoSolicitud(entidad.getCodigoSolicitud());

        if (entidad.getTipoTramite() != null) {
            dto.setIdTipoTramite(entidad.getTipoTramite().getIdTipoTramite());
            dto.setNombreTramite(entidad.getTipoTramite().getNombreTramite());
        }

        if (entidad.getUsuario() != null) {
            dto.setIdUsuario(entidad.getUsuario().getIdUsuario());
            dto.setNombresUsuario(entidad.getUsuario().getNombres());
            dto.setApellidosUsuario(entidad.getUsuario().getApellidos());
        }

        if (entidad.getCarrera() != null) {
            dto.setIdCarrera(entidad.getCarrera().getIdCarrera());
            dto.setNombreCarrera(entidad.getCarrera().getNombreCarrera());
        }

        if (entidad.getFacultad() != null) {
            dto.setIdFacultad(entidad.getFacultad().getIdFacultad());
            dto.setNombreFacultad(entidad.getFacultad().getNombreFacultad());
        }

        dto.setDetallesSolicitud(entidad.getDetallesSolicitud());
        dto.setPrioridad(entidad.getPrioridad());
        dto.setEstadoActual(entidad.getEstadoActual());

        if (entidad.getPasoActual() != null) {
            dto.setIdPasoActual(entidad.getPasoActual().getIdPaso());
        }

        if (entidad.getAsignadoA() != null) {
            dto.setIdAsignadoA(entidad.getAsignadoA().getIdUsuario());
            dto.setNombresAsignadoA(entidad.getAsignadoA().getNombres() + " " + entidad.getAsignadoA().getApellidos());
        }

        dto.setFechaEstimadaFin(entidad.getFechaEstimadaFin());
        dto.setFechaRealFin(entidad.getFechaRealFin());
        dto.setResolucion(entidad.getResolucion());
        dto.setCreadoEn(entidad.getCreadoEn());
        dto.setActualizadoEn(entidad.getActualizadoEn());

        return dto;
    }
}

