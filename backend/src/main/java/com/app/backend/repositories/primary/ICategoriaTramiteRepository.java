package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.CategoriaTramite;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio para CategoriaTramite del sistema principal.
 */
public interface ICategoriaTramiteRepository extends JpaRepository<CategoriaTramite, Integer> {
    List<CategoriaTramite> findByEstaActivoTrue();
}

