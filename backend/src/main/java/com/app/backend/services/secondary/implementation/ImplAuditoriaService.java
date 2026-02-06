package com.app.backend.services.secondary.implementation;

import com.app.backend.dtos.secondary.AuditoriaDTO;
import com.app.backend.entities.secondary.Auditoria;
import com.app.backend.repositories.secondary.IAuditoriaRepository;
import com.app.backend.repositories.secondary.ITipoEventoAuditoriaRepository;
import com.app.backend.repositories.secondary.IUsuarioSecundarioRepository;
import com.app.backend.services.secondary.IAuditoriaService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para Auditoria.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta
 * y evitar LazyInitializationException al acceder a relaciones LAZY.
 */
@Service
@RequiredArgsConstructor
public class ImplAuditoriaService implements IAuditoriaService {

    private final IAuditoriaRepository auditoriaRepository;
    private final ITipoEventoAuditoriaRepository tipoEventoRepository;
    private final IUsuarioSecundarioRepository usuarioRepository;

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<AuditoriaDTO> obtenerTodasLasAuditorias() {
        return auditoriaRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<AuditoriaDTO> obtenerPorUsuario(Integer idUsuario) {
        return auditoriaRepository.findByUsuario_IdUsuario(idUsuario).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<AuditoriaDTO> obtenerPorTipoEvento(Integer idTipoEvento) {
        return auditoriaRepository.findByTipoEvento_IdTipoEvento(idTipoEvento).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<AuditoriaDTO> obtenerPorTabla(String tablaAfectada) {
        return auditoriaRepository.findByTablaAfectada(tablaAfectada).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<AuditoriaDTO> obtenerPorRangoFechas(Date fechaInicio, Date fechaFin) {
        return auditoriaRepository.findByFechaEventoBetween(fechaInicio, fechaFin).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager")
    public AuditoriaDTO guardarAuditoria(@NonNull AuditoriaDTO dto) {
        Auditoria entidad = new Auditoria();

        if (dto.getIdTipoEvento() != null) {
            tipoEventoRepository.findById(dto.getIdTipoEvento())
                    .ifPresent(entidad::setTipoEvento);
        }

        if (dto.getIdUsuario() != null) {
            usuarioRepository.findById(dto.getIdUsuario())
                    .ifPresent(entidad::setUsuario);
        }

        entidad.setTablaAfectada(dto.getTablaAfectada());
        entidad.setIdRegistroAfectado(dto.getIdRegistroAfectado());
        entidad.setValoresAnteriores(dto.getValoresAnteriores());
        entidad.setValoresNuevos(dto.getValoresNuevos());
        entidad.setDireccionIp(dto.getDireccionIp());
        entidad.setAgenteUsuario(dto.getAgenteUsuario());
        entidad.setSistemaOrigen(dto.getSistemaOrigen());
        entidad.setInfoAdicional(dto.getInfoAdicional());
        entidad.setFechaEvento(new Date());
        entidad.setCreadoEn(new Date());

        Auditoria guardada = auditoriaRepository.save(entidad);
        return convertirADto(guardada);
    }

    private AuditoriaDTO convertirADto(@NonNull Auditoria entidad) {
        AuditoriaDTO dto = new AuditoriaDTO();
        dto.setIdAuditoria(entidad.getIdAuditoria());

        if (entidad.getTipoEvento() != null) {
            dto.setIdTipoEvento(entidad.getTipoEvento().getIdTipoEvento());
            dto.setCodigoEvento(entidad.getTipoEvento().getCodigoEvento());
            dto.setNombreEvento(entidad.getTipoEvento().getNombreEvento());
        }

        if (entidad.getUsuario() != null) {
            dto.setIdUsuario(entidad.getUsuario().getIdUsuario());
            dto.setNombreUsuario(entidad.getUsuario().getNombres() + " " + entidad.getUsuario().getApellidos());
        }

        dto.setTablaAfectada(entidad.getTablaAfectada());
        dto.setIdRegistroAfectado(entidad.getIdRegistroAfectado());
        dto.setValoresAnteriores(entidad.getValoresAnteriores());
        dto.setValoresNuevos(entidad.getValoresNuevos());
        dto.setDireccionIp(entidad.getDireccionIp());
        dto.setAgenteUsuario(entidad.getAgenteUsuario());
        dto.setSistemaOrigen(entidad.getSistemaOrigen());
        dto.setInfoAdicional(entidad.getInfoAdicional());
        dto.setFechaEvento(entidad.getFechaEvento());
        dto.setCreadoEn(entidad.getCreadoEn());

        return dto;
    }
}

