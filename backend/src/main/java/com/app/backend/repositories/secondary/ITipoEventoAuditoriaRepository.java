package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.TipoEventoAuditoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para TipoEventoAuditoria del sistema secundario (SGA).
 */
public interface ITipoEventoAuditoriaRepository extends JpaRepository<TipoEventoAuditoria, Integer> {
    Optional<TipoEventoAuditoria> findByCodigoEvento(String codigoEvento);
    List<TipoEventoAuditoria> findByEstaActivoTrue();
    List<TipoEventoAuditoria> findBySeveridad(String severidad);
}

