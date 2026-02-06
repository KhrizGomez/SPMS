package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.ConfiguracionUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para ConfiguracionUsuario del sistema principal.
 */
public interface IConfiguracionUsuarioRepository extends JpaRepository<ConfiguracionUsuario, Integer> {
}

