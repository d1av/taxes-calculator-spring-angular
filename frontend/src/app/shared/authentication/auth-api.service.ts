import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';
import AuthRequest from './models/auth-request.types';
import LoggedUser from './models/logged-user.interface';
import RegisterRequest from './models/register-request.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {

  constructor (
    private router: Router,
    private _apiService: ApiService
  ) { }

  async register(credentials: RegisterRequest): Promise<void> {
    const registerResponse = await this._apiService.post('auth/register', credentials);
    if (registerResponse.status === 201) {
      alert('Usuário registrado com sucesso.');
      this.router.navigateByUrl('/');
      return;
    }
    alert(registerResponse.message);
  }

  async login(credentials: AuthRequest): Promise<void> {
    const loginResponse = await this._apiService.post('auth/login', credentials);
    if (!loginResponse.token) {
      throw new Error('Login Inválido');
    }

    localStorage.setItem('userId', loginResponse.userId);
    localStorage.setItem('token', loginResponse.token);
    localStorage.setItem('name', credentials.name);

    this.router.navigateByUrl('/');
  }

  isLogged(): boolean {
    return localStorage.getItem('token') !== null;
  }

  public getLoggedUser(): LoggedUser | null {
    if (!this.isLogged()) return null;

    return {
      userId: localStorage.getItem('userId'),
      token: localStorage.getItem('token'),
      name: localStorage.getItem('name')
    } as LoggedUser;
  }

  public logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('name');
    localStorage.removeItem('userId');

    this.router.navigateByUrl('auth/login');
  }
}
