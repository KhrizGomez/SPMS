package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Configuracion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para Configuracion del sistema primario.
 */
public interface IConfiguracionRepository extends JpaRepository<Configuracion, Integer> {
}
