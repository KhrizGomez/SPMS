package com.app.backend.repositories.primary;

import com.app.backend.entities.primary.Credenciales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para Credenciales del sistema primario.
 */
public interface ICredencialesRepository extends JpaRepository<Credenciales, Integer> {
    Optional<Credenciales> findByUsuario_IdUsuario(Integer idUsuario);
}
