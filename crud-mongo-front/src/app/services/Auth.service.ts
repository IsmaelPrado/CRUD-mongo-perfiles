import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor() {}

  isAuthenticated(): boolean {
    // Verifica si el token está presente y no está expirado
    const token = localStorage.getItem('token');
    if (token) {
      // Decodifica el token y verifica la expiración
      const tokenPayload = JSON.parse(atob(token.split('.')[1]));
      return tokenPayload.exp > Date.now() / 1000;
    }
    return false;
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }
}
