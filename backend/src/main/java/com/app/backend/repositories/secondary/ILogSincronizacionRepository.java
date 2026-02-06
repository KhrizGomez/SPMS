package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.LogSincronizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio para LogSincronizacion del sistema secundario (SGA).
 */
public interface ILogSincronizacionRepository extends JpaRepository<LogSincronizacion, Integer> {
    List<LogSincronizacion> findBySistemaDestino(String sistemaDestino);
    List<LogSincronizacion> findByEstado(String estado);
    List<LogSincronizacion> findByTipoEntidad(String tipoEntidad);
    List<LogSincronizacion> findByTipoEntidadAndIdEntidadLocal(String tipoEntidad, Integer idEntidadLocal);
    List<LogSincronizacion> findBySolicitadoPor_IdUsuario(Integer idUsuario);
}

