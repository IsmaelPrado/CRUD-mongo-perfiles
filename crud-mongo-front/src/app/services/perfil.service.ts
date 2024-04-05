import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, delay } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Perfil } from '../model/perfil';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {

  perfilURL = environment.apiRestURL + '/perfil';
  loading = false;

  constructor(private httpClient: HttpClient) {
  }

  public list(): Observable<Perfil[]> {
    this.loading = true;
    return this.httpClient.get<Perfil[]>(this.perfilURL).pipe(
      delay(1000)
    );
  }
  public detail(id: number): Observable<Perfil> {
    const url = this.perfilURL + `/${id}`;
    console.log('URL para detalle:', url); // Agrega esta línea
    return this.httpClient.get<Perfil>(url);
  }


  public create(perfil: Perfil): Observable<any> {
    return this.httpClient.post<any>(this.perfilURL, perfil);
  }

  public update(id: number, perfil: Perfil): Observable<any> {
    return this.httpClient.put<any>(this.perfilURL + `/${id}`, perfil);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.perfilURL + `/${id}`);
  }

  public finishLoading(): void {
    this.loading = false;
  }
}
