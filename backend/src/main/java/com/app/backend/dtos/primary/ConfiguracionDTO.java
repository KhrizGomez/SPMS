package com.app.backend.dtos.primary;

import lombok.Data;

/**
 * DTO para Configuracion del sistema primario.
 */
@Data
public class ConfiguracionDTO {
    private Integer idConfiguracion;
    private byte[] fotoPerfil;
    private byte[] firmaEscaneada;
    private Boolean canalSms;
    private Boolean canalCorreo;
    private Boolean canalWhatsapp;
}

