/**
 * Modelo para la solicitud de registro de usuario interno.
 * Solo requiere la cédula ya que los datos se obtienen del sistema institucional.
 */
export interface RegistroUsuarioInterno {
    cedula: string;  // Cédula del usuario
}

/**
 * Modelo para la respuesta del registro de usuario.
 * Contiene información del usuario creado y las credenciales generadas.
 */
export interface RespuestaRegistroUsuario {
    // Información del usuario
    idUsuario?: number;
    nombres?: string;
    apellidos?: string;
    cedula?: string;
    correoInstitucional?: string;
    correoPersonal?: string;
    rol?: string;

    // Credenciales generadas (solo se muestran una vez)
    usuarioGenerado?: string;
    contrasenaGenerada?: string;

    // Estado del registro
    exitoso: boolean;
    mensaje: string;
}

/**
 * Modelo para el usuario del sistema primario.
 */
export interface UsuarioPrimario {
    idUsuario?: number;
    nombres: string;
    apellidos: string;
    cedula: string;
    correoInstitucional: string;
    correoPersonal?: string;
    estado?: boolean;
    rol?: string;
}

/**
 * Modelo para el usuario del sistema secundario (institucional).
 */
export interface UsuarioSecundario {
    idUsuario?: number;
    cedulaIdentidad: string;
    nombreUsuario: string;
    apellidoUsuario: string;
    correoPersonal: string;
    correoInstitucional: string;
    telefono?: string;
    rol?: string;
    fechaRegistro?: Date;
}
