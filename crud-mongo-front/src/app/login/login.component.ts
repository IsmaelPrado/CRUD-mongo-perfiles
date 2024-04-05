import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private http: HttpClient,
              private router: Router) {}

  login() {
    // Verifica que se ingresen el nombre de usuario y la contraseña
    if (!this.username || !this.password) {
      this.errorMessage = 'Por favor, ingrese nombre de usuario y contraseña';
      return;
    }

    const userData = {
      username: this.username,
      password: this.password
    };

    this.http.post<any>('http://localhost:8080/api/usuarios/login', userData, { responseType: 'text' as 'json' })
      .subscribe(
        response => {
          console.log('Token:', response); // Imprimir el token en la consola
          // Verifica si la respuesta contiene un token JWT válido
          if (response && typeof response === 'string' && response.startsWith('eyJ')) {
            // Almacena el token en el localStorage
            localStorage.setItem('token', response);
            // Si es un token válido, redirige al usuario a la página principal
            this.router.navigate(['/list']);
          } else {
            // Si no es un token válido, muestra un mensaje de error
            this.errorMessage = 'Inicio de sesión fallido';
          }
        },
        error => {
          this.errorMessage = 'Ocurrió un error al intentar iniciar sesión';
          console.error(error);
        }
      );
  }

}
