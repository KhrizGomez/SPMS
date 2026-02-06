package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

/**
 * Entidad que representa la tabla 'configuraciones_usuario' en la BD principal.
 */
@Data
@Entity
@Table(name = "configuraciones_usuario")
public class ConfiguracionUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion")
    private Integer idConfiguracion;

    @Column(name = "ruta_foto_perfil", length = 500)
    private String rutaFotoPerfil;

    @Column(name = "ruta_firma_escaneada", length = 500)
    private String rutaFirmaEscaneada;

    @Column(name = "notificar_sms")
    private Boolean notificarSms = false;

    @Column(name = "notificar_email")
    private Boolean notificarEmail = true;

    @Column(name = "notificar_whatsapp")
    private Boolean notificarWhatsapp = false;

    @Column(name = "notificar_push")
    private Boolean notificarPush = true;

    @Column(name = "idioma", length = 10)
    private String idioma = "es";

    @Column(name = "zona_horaria", length = 50)
    private String zonaHoraria = "America/Guayaquil";

    @Column(name = "creado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creadoEn;

    @Column(name = "actualizado_en")
    @Temporal(TemporalType.TIMESTAMP)
    private Date actualizadoEn;
}

