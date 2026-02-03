import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { UserManagement } from './components/user-management/user-management';

export const routes: Routes = [
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: Login },
    { path: 'user-management', component: UserManagement }
];
