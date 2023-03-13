import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../services/api.service';
import AuthRequest from './models/auth-request.types';
import LoggedUser from './models/logged-user.interface';

@Injectable({
  providedIn: 'root'
})
export class AuthApiService {

  constructor(
    private router: Router,
    private _apiService: ApiService
  ) { }

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
    } as LoggedUser
  }
}
