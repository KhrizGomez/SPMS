package com.app.backend.dtos.primary;

import lombok.Data;

/**
 * DTO para la solicitud de registro de usuario interno.
 * Solo requiere la cédula ya que los datos se obtienen del sistema secundario.
 */
@Data
public class RegistroUsuarioInternoDTO {
    // Cédula del usuario a registrar (se buscará en la base de datos secundaria)
    private String cedula;
}
