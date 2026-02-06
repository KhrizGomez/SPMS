package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio para Solicitud del sistema principal.
 */
public interface ISolicitudRepository extends JpaRepository<Solicitud, Integer> {
    Optional<Solicitud> findByCodigoSolicitud(String codigoSolicitud);
    List<Solicitud> findByUsuario_IdUsuario(Integer idUsuario);
    List<Solicitud> findByEstadoActual(String estadoActual);
    List<Solicitud> findByAsignadoA_IdUsuario(Integer idUsuario);
    List<Solicitud> findByTipoTramite_IdTipoTramite(Integer idTipoTramite);
    List<Solicitud> findByCarrera_IdCarrera(Integer idCarrera);
    List<Solicitud> findByFacultad_IdFacultad(Integer idFacultad);
}

