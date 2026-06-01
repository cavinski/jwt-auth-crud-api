import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})

export class AuthService {

  constructor(
    private http: HttpClient
  ) {}

  private api = environment.apiUrl;

  register(data:any): Observable<any> {
    return this.http.post(`${this.api}/auth/register`, data);
  }

  login(data:any): Observable<any> {
    return this.http.post<any>(`${this.api}/auth/login`, data);
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.removeItem('token');
  }

  isAuthenticated() {
    return !!this.getToken();
  }

}
