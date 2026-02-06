package com.app.backend.services.secondary.implementation;

import com.app.backend.dtos.secondary.LogSincronizacionDTO;
import com.app.backend.entities.secondary.LogSincronizacion;
import com.app.backend.repositories.secondary.ILogSincronizacionRepository;
import com.app.backend.repositories.secondary.IUsuarioSecundarioRepository;
import com.app.backend.services.secondary.ILogSincronizacionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación del servicio para LogSincronizacion.
 * Se usa @Transactional para mantener la sesión de Hibernate abierta.
 */
@Service
@RequiredArgsConstructor
public class ImplLogSincronizacionService implements ILogSincronizacionService {

    private final ILogSincronizacionRepository logRepository;
    private final IUsuarioSecundarioRepository usuarioRepository;

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<LogSincronizacionDTO> obtenerTodosLosLogs() {
        return logRepository.findAll().stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<LogSincronizacionDTO> obtenerPorSistemaDestino(String sistemaDestino) {
        return logRepository.findBySistemaDestino(sistemaDestino).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<LogSincronizacionDTO> obtenerPorEstado(String estado) {
        return logRepository.findByEstado(estado).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager", readOnly = true)
    public List<LogSincronizacionDTO> obtenerPorTipoEntidad(String tipoEntidad) {
        return logRepository.findByTipoEntidad(tipoEntidad).stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(value = "secondaryTransactionManager")
    public LogSincronizacionDTO guardarLog(@NonNull LogSincronizacionDTO dto) {
        LogSincronizacion entidad = new LogSincronizacion();

        entidad.setSistemaDestino(dto.getSistemaDestino());
        entidad.setTipoEntidad(dto.getTipoEntidad());
        entidad.setIdEntidadLocal(dto.getIdEntidadLocal());
        entidad.setIdEntidadExterna(dto.getIdEntidadExterna());
        entidad.setAccion(dto.getAccion());
        entidad.setEstado(dto.getEstado() != null ? dto.getEstado() : "pendiente");
        entidad.setDatosEnviados(dto.getDatosEnviados());
        entidad.setRespuestaRecibida(dto.getRespuestaRecibida());
        entidad.setMensajeError(dto.getMensajeError());
        entidad.setIntentos(dto.getIntentos() != null ? dto.getIntentos() : 0);

        if (dto.getIdUsuarioSolicitante() != null) {
            usuarioRepository.findById(dto.getIdUsuarioSolicitante())
                    .ifPresent(entidad::setSolicitadoPor);
        }

        entidad.setFechaSolicitud(new Date());
        entidad.setCreadoEn(new Date());

        LogSincronizacion guardado = logRepository.save(entidad);
        return convertirADto(guardado);
    }

    @Override
    @Transactional(value = "secondaryTransactionManager")
    public LogSincronizacionDTO actualizarEstado(Integer idSync, String nuevoEstado) {
        LogSincronizacion entidad = logRepository.findById(idSync)
                .orElseThrow(() -> new RuntimeException("Log de sincronización no encontrado: " + idSync));

        entidad.setEstado(nuevoEstado);
        entidad.setFechaProcesado(new Date());
        entidad.setIntentos(entidad.getIntentos() + 1);

        LogSincronizacion actualizado = logRepository.save(entidad);
        return convertirADto(actualizado);
    }

    private LogSincronizacionDTO convertirADto(@NonNull LogSincronizacion entidad) {
        LogSincronizacionDTO dto = new LogSincronizacionDTO();
        dto.setIdSync(entidad.getIdSync());
        dto.setSistemaDestino(entidad.getSistemaDestino());
        dto.setTipoEntidad(entidad.getTipoEntidad());
        dto.setIdEntidadLocal(entidad.getIdEntidadLocal());
        dto.setIdEntidadExterna(entidad.getIdEntidadExterna());
        dto.setAccion(entidad.getAccion());
        dto.setEstado(entidad.getEstado());
        dto.setDatosEnviados(entidad.getDatosEnviados());
        dto.setRespuestaRecibida(entidad.getRespuestaRecibida());
        dto.setMensajeError(entidad.getMensajeError());
        dto.setIntentos(entidad.getIntentos());

        if (entidad.getSolicitadoPor() != null) {
            dto.setIdUsuarioSolicitante(entidad.getSolicitadoPor().getIdUsuario());
            dto.setNombreUsuarioSolicitante(entidad.getSolicitadoPor().getNombres() + " " +
                                           entidad.getSolicitadoPor().getApellidos());
        }

        dto.setFechaSolicitud(entidad.getFechaSolicitud());
        dto.setFechaProcesado(entidad.getFechaProcesado());
        dto.setCreadoEn(entidad.getCreadoEn());

        return dto;
    }
}

