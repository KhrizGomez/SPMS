package com.app.backend.entities.primary;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

/**
 * Entidad que representa la tabla 'configuraciones' en la BD primaria.
 */
@Data
@Entity
@Table(name = "configuraciones")
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion")
    private Integer idConfiguracion;

    @Lob
    @Column(name = "foto_perfil", columnDefinition = "bytea")
    private byte[] fotoPerfil;

    @Lob
    @Column(name = "firma_escaneada", columnDefinition = "bytea")
    private byte[] firmaEscaneada;

    @Column(name = "canal_sms")
    private Boolean canalSms;

    @Column(name = "canal_correo")
    private Boolean canalCorreo;

    @Column(name = "canal_whatsapp")
    private Boolean canalWhatsapp;

    @OneToMany(mappedBy = "configuracion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Usuario> usuarios;
}

