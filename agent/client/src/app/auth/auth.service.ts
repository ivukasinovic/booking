import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable()
export class AuthService {
  constructor(private router: Router, private http: HttpClient) {
  }

  public isAuthenticated(): boolean {
    return true;
  }


  login(username: string, password: string) {
    return this.http.post('api/login', {username: username, password: password});
  }

  logout() {
    localStorage.clear();

  }
}
