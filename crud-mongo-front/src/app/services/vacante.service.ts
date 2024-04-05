import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Vacante } from '../model/vacante';

@Injectable({
  providedIn: 'root'
})
export class VacanteService {

  vacanteURL = environment.apiRestURL;

  constructor(private httpClient: HttpClient) { }

  public listByPerfilId(perfilId: number): Observable<Vacante[]> {
    return this.httpClient.get<Vacante[]>(`${this.vacanteURL}/perfil/${perfilId}/vacante/all`);
  }

  public detailByPerfilIdAndVacanteId(perfilId: number, vacanteId: number): Observable<Vacante> {
    return this.httpClient.get<Vacante>(`${this.vacanteURL}/perfil/${perfilId}/vacante/${vacanteId}`);
  }

  public create(perfilId: number, vacante: Vacante): Observable<any> {
    return this.httpClient.post<any>(`${this.vacanteURL}/perfil/${perfilId}/vacante`, vacante);
  }

  public update(perfilId: number, vacanteId: number, vacante: Vacante): Observable<any> {
    return this.httpClient.put<any>(`${this.vacanteURL}/perfil/${perfilId}/vacante/${vacanteId}`, vacante);
  }

  public delete(perfilId: number, vacanteId: number): Observable<any> {
    return this.httpClient.delete<any>(`${this.vacanteURL}/perfil/${perfilId}/vacante/${vacanteId}`);
  }
}
