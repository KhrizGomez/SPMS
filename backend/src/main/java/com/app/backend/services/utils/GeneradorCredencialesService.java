package com.app.backend.services.utils;

import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.text.Normalizer;

/**
 * Servicio de utilidades para generación de credenciales.
 * Genera nombres de usuario y contraseñas aleatorias basadas en los datos del usuario.
 */
@Service
public class GeneradorCredencialesService {

    // Caracteres permitidos para la contraseña
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITOS = "0123456789";
    private static final String ESPECIALES = "!@#$%&*";

    private final SecureRandom aleatorio = new SecureRandom();

    /**
     * Genera un nombre de usuario basado en el nombre y apellido.
     * Formato: primera letra del nombre + apellido + número aleatorio
     * Ejemplo: Juan Pérez -> jperez123
     *
     * @param nombres Nombres del usuario
     * @param apellidos Apellidos del usuario
     * @return Nombre de usuario generado
     */
    public String generarNombreUsuario(String nombres, String apellidos) {
        // Limpiar y normalizar los strings (quitar acentos y caracteres especiales)
        String nombresLimpios = normalizarCadena(nombres).toLowerCase();
        String apellidosLimpios = normalizarCadena(apellidos).toLowerCase();

        // Obtener primera letra del nombre
        String primeraLetra = nombresLimpios.substring(0, 1);

        // Obtener primer apellido (antes del espacio si hay más de uno)
        String primerApellido = apellidosLimpios.split("\\s+")[0];

        // Generar número aleatorio de 3 dígitos
        int numeroAleatorio = aleatorio.nextInt(900) + 100; // 100-999

        return primeraLetra + primerApellido + numeroAleatorio;
    }

    /**
     * Genera una contraseña aleatoria segura.
     * La contraseña contiene: mayúsculas, minúsculas, números y caracteres especiales.
     *
     * @param longitud Longitud de la contraseña (mínimo 8)
     * @return Contraseña generada
     */
    public String generarContrasena(int longitud) {
        if (longitud < 8) {
            longitud = 8; // Longitud mínima por seguridad
        }

        StringBuilder contrasena = new StringBuilder();

        // Asegurar al menos un carácter de cada tipo
        contrasena.append(MAYUSCULAS.charAt(aleatorio.nextInt(MAYUSCULAS.length())));
        contrasena.append(MINUSCULAS.charAt(aleatorio.nextInt(MINUSCULAS.length())));
        contrasena.append(DIGITOS.charAt(aleatorio.nextInt(DIGITOS.length())));
        contrasena.append(ESPECIALES.charAt(aleatorio.nextInt(ESPECIALES.length())));

        // Completar el resto de la contraseña con caracteres aleatorios
        String todosCaracteres = MAYUSCULAS + MINUSCULAS + DIGITOS + ESPECIALES;
        for (int i = 4; i < longitud; i++) {
            contrasena.append(todosCaracteres.charAt(aleatorio.nextInt(todosCaracteres.length())));
        }

        // Mezclar los caracteres para que no siempre empiecen igual
        return mezclarCadena(contrasena.toString());
    }

    /**
     * Genera una contraseña con longitud por defecto de 10 caracteres.
     * @return Contraseña generada
     */
    public String generarContrasena() {
        return generarContrasena(10);
    }

    /**
     * Normaliza un string quitando acentos y caracteres especiales.
     * Ejemplo: "José María" -> "Jose Maria"
     */
    private String normalizarCadena(String entrada) {
        if (entrada == null) return "";

        // Normalizar y quitar acentos
        String normalizada = Normalizer.normalize(entrada, Normalizer.Form.NFD);
        return normalizada.replaceAll("[^\\p{ASCII}]", "")  // Quitar caracteres no ASCII
                        .replaceAll("[^a-zA-Z\\s]", "")     // Solo letras y espacios
                        .trim();
    }

    /**
     * Mezcla aleatoriamente los caracteres de una cadena.
     */
    private String mezclarCadena(String entrada) {
        char[] caracteres = entrada.toCharArray();
        for (int i = caracteres.length - 1; i > 0; i--) {
            int j = aleatorio.nextInt(i + 1);
            char temp = caracteres[i];
            caracteres[i] = caracteres[j];
            caracteres[j] = temp;
        }
        return new String(caracteres);
    }
}
