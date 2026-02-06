import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { RespuestaRegistroUsuario } from '../../models/user.model';

@Component({
    selector: 'app-login',
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './login.html',
    styleUrl: './login.css',
})
export class Login implements OnInit {
    formularioRegistro!: FormGroup;
    enviando = false;

    // Variables para mostrar resultado del registro
    resultadoRegistro: RespuestaRegistroUsuario | null = null;
    mostrarCredenciales = false;

    constructor(
        private router: Router,
        private fb: FormBuilder,
        private authService: AuthService  // Inyectar servicio de autenticación
    ) {}

    ngOnInit(): void {
        this.inicializarFormularioRegistro();
    }

    get esExterno(): boolean {
        return this.formularioRegistro?.get('esExterno')?.value || false;
    }

    private inicializarFormularioRegistro(): void {
        this.formularioRegistro = this.fb.group({
            esExterno: [false],
            cedulaIdentidad: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(13)]],
            nombreUsuario: [''],
            apellidoUsuario: [''],
            correoPersonal: [''],
            correoInstitucional: [''],
            telefono: ['']
        });
    }

    alCambiarExterno(): void {
        if (this.esExterno) {
            // Agregar validaciones para usuario externo
            this.formularioRegistro.get('nombreUsuario')?.setValidators([Validators.required, Validators.minLength(2)]);
            this.formularioRegistro.get('apellidoUsuario')?.setValidators([Validators.required, Validators.minLength(2)]);
            this.formularioRegistro.get('correoPersonal')?.setValidators([Validators.required, Validators.email]);
            this.formularioRegistro.get('correoInstitucional')?.setValidators([Validators.required, Validators.email]);
            this.formularioRegistro.get('telefono')?.setValidators([Validators.pattern(/^[0-9]{10}$/)]);
        } else {
            // Quitar validaciones para usuario interno
            this.formularioRegistro.get('nombreUsuario')?.clearValidators();
            this.formularioRegistro.get('apellidoUsuario')?.clearValidators();
            this.formularioRegistro.get('correoPersonal')?.clearValidators();
            this.formularioRegistro.get('correoInstitucional')?.clearValidators();
            this.formularioRegistro.get('telefono')?.clearValidators();

            // Limpiar valores
            this.formularioRegistro.patchValue({
                nombreUsuario: '',
                apellidoUsuario: '',
                correoPersonal: '',
                correoInstitucional: '',
                telefono: ''
            });
        }

        // Actualizar estado de validación
        this.formularioRegistro.get('nombreUsuario')?.updateValueAndValidity();
        this.formularioRegistro.get('apellidoUsuario')?.updateValueAndValidity();
        this.formularioRegistro.get('correoPersonal')?.updateValueAndValidity();
        this.formularioRegistro.get('correoInstitucional')?.updateValueAndValidity();
        this.formularioRegistro.get('telefono')?.updateValueAndValidity();
    }

    esFormularioValido(): boolean {
        const cedula = this.formularioRegistro.get('cedulaIdentidad');
        if (!cedula?.valid) return false;

        if (this.esExterno) {
            return this.formularioRegistro.valid;
        }

        return true;
    }

    esCampoInvalido(nombreCampo: string): boolean {
        const campo = this.formularioRegistro.get(nombreCampo);
        return campo ? campo.invalid && (campo.dirty || campo.touched) : false;
    }

    /**
     * Maneja el envío del formulario de registro.
     * Si es usuario interno, llama al servicio para registrar con la cédula.
     * Si es externo, envía todos los datos del formulario.
     */
    alRegistrar(): void {
        if (this.esFormularioValido()) {
            this.enviando = true;
            this.resultadoRegistro = null;

            if (this.esExterno) {
                // Usuario externo: TODO implementar registro externo
                this.registrarUsuarioExterno();
            } else {
                // Usuario interno: registrar usando la cédula
                this.registrarUsuarioInterno();
            }
        } else {
            this.marcarTodosLosCamposComoTocados();
        }
    }

    /**
     * Registra un usuario interno usando el servicio AuthService.
     * Busca los datos en el sistema institucional y crea la cuenta.
     */
    private registrarUsuarioInterno(): void {
        const cedula = this.formularioRegistro.get('cedulaIdentidad')?.value;

        console.log('Registrando usuario interno con cédula:', cedula);

        this.authService.registrarUsuarioInterno(cedula).subscribe({
            next: (respuesta: RespuestaRegistroUsuario) => {
                console.log('Respuesta del servidor:', respuesta);
                this.enviando = false;
                this.resultadoRegistro = respuesta;

                if (respuesta.exitoso) {
                    // Mostrar credenciales generadas
                    this.mostrarCredenciales = true;
                    // No cerrar el modal para que el usuario vea sus credenciales
                } else {
                    // Mostrar mensaje de error
                    alert(respuesta.mensaje);
                }
            },
            error: (error: Error) => {
                console.error('Error al registrar:', error);
                this.enviando = false;
                alert(error.message || 'Error al registrar el usuario. Intente nuevamente.');
            }
        });
    }

    /**
     * Registra un usuario externo (TODO: implementar).
     */
    private registrarUsuarioExterno(): void {
        // TODO: Implementar registro de usuario externo
        const datosUsuario = {
            ...this.formularioRegistro.value,
            fechaRegistro: new Date()
        };

        console.log('Datos de usuario externo:', datosUsuario);

        // Simulación temporal
        setTimeout(() => {
            this.enviando = false;
            this.cerrarModal();
            this.reiniciarFormulario();
            alert('Solicitud enviada correctamente. Un administrador revisará su cuenta.');
        }, 1500);
    }

    /**
     * Cierra el modal y resetea el formulario después de ver las credenciales.
     */
    cerrarYReiniciar(): void {
        this.cerrarModal();
        this.reiniciarFormulario();
        this.mostrarCredenciales = false;
        this.resultadoRegistro = null;
    }

    /**
     * Copia un texto al portapapeles del usuario.
     * Muestra una notificación breve de confirmación.
     */
    copiarAlPortapapeles(texto: string): void {
        navigator.clipboard.writeText(texto).then(() => {
            // Mostrar notificación de copiado (usando alert simple por ahora)
            // TODO: Implementar toast notification
            console.log('Copiado al portapapeles:', texto);
        }).catch(err => {
            console.error('Error al copiar:', err);
        });
    }

    private marcarTodosLosCamposComoTocados(): void {
        Object.keys(this.formularioRegistro.controls).forEach(key => {
            this.formularioRegistro.get(key)?.markAsTouched();
        });
    }

    private reiniciarFormulario(): void {
        this.formularioRegistro.reset({ esExterno: false });
        this.alCambiarExterno();
    }

    private cerrarModal(): void {
        const elementoModal = document.getElementById('staticBackdrop');
        if (elementoModal) {
            const bootstrap = (window as any).bootstrap;
            const modal = bootstrap.Modal.getInstance(elementoModal);
            modal?.hide();
        }
    }

    iniciarSesion(): void {
        this.router.navigate(['/user-management']);
    }
}
