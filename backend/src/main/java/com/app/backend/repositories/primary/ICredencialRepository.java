package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para Credencial del sistema principal.
 */
public interface ICredencialRepository extends JpaRepository<Credencial, Integer> {
}

