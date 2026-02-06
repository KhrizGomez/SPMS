package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio para Notificacion del sistema principal.
 */
public interface INotificacionRepository extends JpaRepository<Notificacion, Integer> {
    List<Notificacion> findByUsuario_IdUsuario(Integer idUsuario);
    List<Notificacion> findByUsuario_IdUsuarioAndEstaLeidaFalse(Integer idUsuario);
    List<Notificacion> findBySolicitud_IdSolicitud(Integer idSolicitud);
    List<Notificacion> findByEstaEnviadaFalse();
}

