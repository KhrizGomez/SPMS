package com.app.backend.repositories.secondary;

import com.app.backend.entities.secondary.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio para Usuario del sistema secundario (SGA).
 */
public interface IUsuarioSecundarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Busca un usuario por su cédula.
     * @param cedula Cédula del usuario
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findByCedula(String cedula);

    /**
     * Busca un usuario por su correo institucional.
     * @param correoInstitucional Correo institucional del usuario
     * @return Optional con el usuario si existe
     */
    Optional<Usuario> findByCorreoInstitucional(String correoInstitucional);
}
