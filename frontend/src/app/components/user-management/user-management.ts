import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-user-management',
    imports: [],
    templateUrl: './user-management.html',
    styleUrl: './user-management.css',
})
export class UserManagement {
    constructor(private router: Router){}
    GoBack(){
        this.router.navigate(['/login'])
    }
}
