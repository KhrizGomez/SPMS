package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.TipoNotificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Repositorio para TipoNotificacion del sistema principal.
 */
public interface ITipoNotificacionRepository extends JpaRepository<TipoNotificacion, Integer> {
    Optional<TipoNotificacion> findByCodigoTipo(String codigoTipo);
}

