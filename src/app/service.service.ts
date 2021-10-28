import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {

  public BASE_URL = environment.base_url;

  cart: any = [];

  constructor(private http : HttpClient) { }

  postData(extendedUrl, data): Observable<any> {
    return this.http.post(this.BASE_URL + extendedUrl, data);
  }

  getData(extendedUrl): Observable<any> {
    return this.http.get(this.BASE_URL + extendedUrl);
  }

  get(extendedUrl, httpHeader: any): Observable<any> {
    return this.http.get(this.BASE_URL + extendedUrl, httpHeader);
  }

  post(extendedUrl, data, httpHeader: any): Observable<any> {
    return this.http.post(this.BASE_URL + extendedUrl, data, httpHeader);
  }
}
