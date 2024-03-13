import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from '../model/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productURL = environment.apiRestURL + '/product';

  constructor(private httpClient: HttpClient) { 
  }

  public list(): Observable<Product[]>{
    return this.httpClient.get<Product[]>(this.productURL);
  }

  public detail(id: number): Observable<Product>{
    return this.httpClient.get<Product>(this.productURL + `/${id}`);
  }

  public create(product: Product): Observable<any>{
    return this.httpClient.post<any>(this.productURL, product);
  }

  public update(id: number, product: Product): Observable<any>{
    return this.httpClient.put<any>(this.productURL + `/${id}`, product);
  }

  public delete(id: number): Observable<any>{
    return this.httpClient.delete<any>(this.productURL + `/${id}`);
  }
}
