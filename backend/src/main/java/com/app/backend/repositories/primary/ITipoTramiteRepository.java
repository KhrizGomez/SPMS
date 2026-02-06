package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.TipoTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio para TipoTramite del sistema principal.
 */
public interface ITipoTramiteRepository extends JpaRepository<TipoTramite, Integer> {
    List<TipoTramite> findByEstaActivoTrue();
    List<TipoTramite> findByCategoria_IdCategoria(Integer idCategoria);
    List<TipoTramite> findByDisponibleExternosTrue();
}

