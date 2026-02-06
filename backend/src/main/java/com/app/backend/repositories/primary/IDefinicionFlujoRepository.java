package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.DefinicionFlujo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio para DefinicionFlujo del sistema principal.
 */
public interface IDefinicionFlujoRepository extends JpaRepository<DefinicionFlujo, Integer> {
    List<DefinicionFlujo> findByEstaActivoTrue();
}

