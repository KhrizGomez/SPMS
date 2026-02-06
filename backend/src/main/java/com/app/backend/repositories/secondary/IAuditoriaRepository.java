package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Date;
import java.util.List;

/**
 * Repositorio para Auditoria del sistema secundario (SGA).
 */
public interface IAuditoriaRepository extends JpaRepository<Auditoria, Long> {
    List<Auditoria> findByUsuario_IdUsuario(Integer idUsuario);
    List<Auditoria> findByTipoEvento_IdTipoEvento(Integer idTipoEvento);
    List<Auditoria> findByTablaAfectada(String tablaAfectada);
    List<Auditoria> findBySistemaOrigen(String sistemaOrigen);
    List<Auditoria> findByFechaEventoBetween(Date fechaInicio, Date fechaFin);
}

