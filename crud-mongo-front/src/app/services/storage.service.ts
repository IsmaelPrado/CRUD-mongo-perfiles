import { Injectable } from '@angular/core';
import { Perfil } from '../model/perfil';

const KEY_PROD = 'prod_update';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  public setProduct(perfil: Perfil): void{
    localStorage.setItem(KEY_PROD, JSON.stringify(perfil));
  }

  public getProduct(): Perfil{
    return JSON.parse(localStorage.getItem(KEY_PROD)!);
  }

  public clear(): void{
    localStorage.removeItem(KEY_PROD);
  }
}
