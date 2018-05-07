import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';

@Injectable()
export class AuthService {
  constructor(private router: Router, private http: HttpClient) {
  }

  public isAuthenticated(): boolean {
    const jwtHelper: JwtHelperService = new JwtHelperService();
    const token = localStorage.getItem('token');
    if (token) {
      return !jwtHelper.isTokenExpired(token);
    }
    return false;
  }


  login(username: string, password: string) {
    return this.http.post('api/login', {username: username, password: password});
  }

  getToken(): string {
    return localStorage.getItem('token');;

  }

  logout() {
    localStorage.clear();

  }
}
