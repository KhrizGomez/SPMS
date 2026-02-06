package com.app.backend.services.utils;

import org.springframework.stereotype.Service;

/**
 * Servicio para el envío de correos electrónicos.
 * Por ahora es una implementación simulada que imprime en consola.
 * TODO: Implementar con JavaMailSender cuando se configure el servidor SMTP.
 */
@Service
public class CorreoService {

    /**
     * Envía un correo con las credenciales de acceso al usuario.
     *
     * @param correoDestino Correo destino
     * @param nombres Nombre del usuario
     * @param nombreUsuario Usuario generado
     * @param contrasena Contraseña generada
     * @return true si el envío fue exitoso
     */
    public boolean enviarCorreoCredenciales(String correoDestino, String nombres, String nombreUsuario, String contrasena) {
        // TODO: Implementar envío real de correo con JavaMailSender
        // Por ahora, simular el envío imprimiendo en consola

        String contenidoCorreo = construirContenidoCredenciales(nombres, nombreUsuario, contrasena);

        System.out.println("========================================");
        System.out.println("SIMULACIÓN DE ENVÍO DE CORREO");
        System.out.println("========================================");
        System.out.println("Para: " + correoDestino);
        System.out.println("Asunto: Credenciales de acceso - Sistema SPMS");
        System.out.println("----------------------------------------");
        System.out.println(contenidoCorreo);
        System.out.println("========================================");

        // Simular envío exitoso
        return true;
    }

    /**
     * Construye el contenido del correo con las credenciales.
     */
    private String construirContenidoCredenciales(String nombres, String nombreUsuario, String contrasena) {
        StringBuilder contenido = new StringBuilder();

        contenido.append("Estimado/a ").append(nombres).append(",\n\n");
        contenido.append("Su cuenta ha sido creada exitosamente en el Sistema SPMS.\n\n");
        contenido.append("Sus credenciales de acceso son:\n\n");
        contenido.append("  Usuario: ").append(nombreUsuario).append("\n");
        contenido.append("  Contraseña: ").append(contrasena).append("\n\n");
        contenido.append("Por seguridad, le recomendamos cambiar su contraseña al iniciar sesión.\n\n");
        contenido.append("Atentamente,\n");
        contenido.append("Sistema SPMS - UTEQ");

        return contenido.toString();
    }

    /**
     * Envía un correo de notificación genérico.
     *
     * @param correoDestino Correo destino
     * @param asunto Asunto del correo
     * @param contenido Contenido del correo
     * @return true si el envío fue exitoso
     */
    public boolean enviarCorreo(String correoDestino, String asunto, String contenido) {
        // TODO: Implementar envío real de correo
        System.out.println("========================================");
        System.out.println("SIMULACIÓN DE ENVÍO DE CORREO");
        System.out.println("Para: " + correoDestino);
        System.out.println("Asunto: " + asunto);
        System.out.println("----------------------------------------");
        System.out.println(contenido);
        System.out.println("========================================");

        return true;
    }
}
