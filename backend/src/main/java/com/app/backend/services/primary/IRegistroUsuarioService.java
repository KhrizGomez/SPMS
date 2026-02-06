package com.app.backend.services.primary;

import com.app.backend.dtos.primary.RegistroUsuarioInternoDTO;
import com.app.backend.dtos.primary.RespuestaRegistroUsuarioDTO;

/**
 * Interfaz del servicio de registro de usuarios.
 * Define las operaciones disponibles para el registro de usuarios internos y externos.
 */
public interface IRegistroUsuarioService {

    /**
     * Registra un usuario interno (de la UTEQ).
     * Busca los datos en la base de datos secundaria usando la cédula,
     * crea el usuario en la base de datos primaria y genera las credenciales.
     *
     * @param registroDTO DTO con la cédula del usuario
     * @return Respuesta con los datos del usuario y credenciales generadas
     */
    RespuestaRegistroUsuarioDTO registrarUsuarioInterno(RegistroUsuarioInternoDTO registroDTO);

    /**
     * Verifica si un usuario ya existe en el sistema primario por su cédula.
     *
     * @param cedula Cédula del usuario
     * @return true si el usuario ya existe
     */
    boolean existeUsuarioPorCedula(String cedula);

    /**
     * Verifica si existe un usuario en el sistema secundario por su cédula.
     *
     * @param cedula Cédula del usuario
     * @return true si el usuario existe en el sistema secundario
     */
    boolean existeUsuarioInternoPorCedula(String cedula);
}
