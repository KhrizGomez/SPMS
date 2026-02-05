import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';

@Component({
    selector: 'app-login',
    imports: [CommonModule, ReactiveFormsModule],
    templateUrl: './login.html',
    styleUrl: './login.css',
})
export class Login implements OnInit {
    registerForm!: FormGroup;
    isSubmitting = false;

    constructor(
        private router: Router,
        private fb: FormBuilder
    ) {}

    ngOnInit(): void {
        this.initRegisterForm();
    }

    private initRegisterForm(): void {
        this.registerForm = this.fb.group({
            cedulaIdentidad: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(13)]],
            nombreUsuario: ['', [Validators.required, Validators.minLength(2)]],
            apellidoUsuario: ['', [Validators.required, Validators.minLength(2)]],
            correoPersonal: ['', [Validators.required, Validators.email]],
            correoInstitucional: ['', [Validators.required, Validators.email]],
            telefono: ['', [Validators.pattern(/^[0-9]{10}$/)]]
        });
    }

    isFieldInvalid(fieldName: string): boolean {
        const field = this.registerForm.get(fieldName);
        return field ? field.invalid && (field.dirty || field.touched) : false;
    }

    onRegister(): void {
        if (this.registerForm.valid) {
            this.isSubmitting = true;
            const userData = {
                ...this.registerForm.value,
                fechaRegistro: new Date()
            };

            console.log('Datos de registro:', userData);

            // TODO: Implementar llamada al servicio para registrar usuario
            // this.userService.register(userData).subscribe({
            //     next: (response) => {
            //         this.closeModal();
            //         this.showSuccessMessage();
            //     },
            //     error: (error) => {
            //         console.error('Error al registrar:', error);
            //     },
            //     complete: () => {
            //         this.isSubmitting = false;
            //     }
            // });

            // Simulación temporal
            setTimeout(() => {
                this.closeModal();
                this.resetForm();
                this.isSubmitting = false;
                alert('Solicitud enviada correctamente. Un administrador revisará su cuenta.');
            }, 1500);
        } else {
            this.markAllFieldsAsTouched();
        }
    }

    private markAllFieldsAsTouched(): void {
        Object.keys(this.registerForm.controls).forEach(key => {
            this.registerForm.get(key)?.markAsTouched();
        });
    }

    private resetForm(): void {
        this.registerForm.reset();
    }

    private closeModal(): void {
        const modalElement = document.getElementById('staticBackdrop');
        if (modalElement) {
            const bootstrap = (window as any).bootstrap;
            const modal = bootstrap.Modal.getInstance(modalElement);
            modal?.hide();
        }
    }

    login(): void {
        this.router.navigate(['/user-management']);
    }
}
