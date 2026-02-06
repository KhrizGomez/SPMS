import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { RegistroUsuarioInterno, RespuestaRegistroUsuario } from '../models/user.model';

/**
 * Servicio para manejar la autenticación y registro de usuarios.
 * Se conecta con los endpoints del backend para:
 * - Registrar usuarios internos (UTEQ)
 * - Verificar existencia de usuarios
 */
@Injectable({
    providedIn: 'root'
})
export class AuthService {
    // URL base del API del backend
    private readonly API_URL = 'http://localhost:8080/api/primary/auth';

    constructor(private http: HttpClient) {}

    /**
     * Registra un usuario interno (UTEQ) usando su cédula.
     * El backend buscará los datos en el sistema institucional y creará la cuenta.
     *
     * @param cedula Cédula del usuario a registrar
     * @returns Observable con la respuesta del registro
     */
    registrarUsuarioInterno(cedula: string): Observable<RespuestaRegistroUsuario> {
        const datosRegistro: RegistroUsuarioInterno = { cedula };

        return this.http.post<RespuestaRegistroUsuario>(
            `${this.API_URL}/registro/interno`,
            datosRegistro
        ).pipe(
            catchError(this.manejarError)
        );
    }

    /**
     * Verifica si un usuario existe en el sistema institucional (BD secundaria).
     * Útil para validar antes de intentar el registro.
     *
     * @param cedula Cédula del usuario
     * @returns Observable<boolean> - true si existe en el sistema institucional
     */
    verificarUsuarioInterno(cedula: string): Observable<boolean> {
        return this.http.get<boolean>(
            `${this.API_URL}/verificar/interno/${cedula}`
        ).pipe(
            catchError(this.manejarError)
        );
    }

    /**
     * Verifica si un usuario ya está registrado en el sistema (BD primaria).
     *
     * @param cedula Cédula del usuario
     * @returns Observable<boolean> - true si ya está registrado
     */
    verificarUsuarioRegistrado(cedula: string): Observable<boolean> {
        return this.http.get<boolean>(
            `${this.API_URL}/verificar/registrado/${cedula}`
        ).pipe(
            catchError(this.manejarError)
        );
    }

    /**
     * Maneja los errores de las peticiones HTTP.
     * Transforma los errores en mensajes amigables para el usuario.
     */
    private manejarError(error: HttpErrorResponse): Observable<never> {
        let mensajeError = 'Ha ocurrido un error inesperado';

        if (error.error instanceof ErrorEvent) {
            // Error del lado del cliente o de red
            mensajeError = `Error: ${error.error.message}`;
        } else {
            // Error del lado del servidor
            if (error.error && error.error.mensaje) {
                mensajeError = error.error.mensaje;
            } else {
                switch (error.status) {
                    case 0:
                        mensajeError = 'No se puede conectar con el servidor. Verifique su conexión.';
                        break;
                    case 400:
                        mensajeError = 'Datos inválidos. Verifique la información ingresada.';
                        break;
                    case 404:
                        mensajeError = 'Usuario no encontrado en el sistema institucional.';
                        break;
                    case 409:
                        mensajeError = 'El usuario ya está registrado en el sistema.';
                        break;
                    case 500:
                        mensajeError = 'Error interno del servidor. Intente más tarde.';
                        break;
                    default:
                        mensajeError = `Error ${error.status}: ${error.statusText}`;
                }
            }
        }

        console.error('AuthService Error:', error);
        return throwError(() => new Error(mensajeError));
    }
}
