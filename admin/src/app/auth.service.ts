import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {JwtHelperService} from '@auth0/angular-jwt';

import {Additional, Tip, User} from './models/user';

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

  register(user: User) {
    return this.http.post('api/register', user);
  }

  registerAgent(user: User) {
    return this.http.post('api/registerAgent', user);
  }

  allComments() {
    return this.http.get('api/comment/all-not');
  }

  deleteComment(br: number) {
    return this.http.delete('api/comment/' + br);
  }

  getUsers() {
    return this.http.get('api/users');
  }

  getUsersSamo() {
    return this.http.get('api/users/samo');
  }

  prihvaticOMMENT(id: number) {
    return this.http.get('api/comment/prihvati/' + id);
  }

  activate(id: number) {
    return this.http.get('api/users/activate/' + id);
  }

  block(id: number) {
    return this.http.get('api/users/deactive/' + id);
  }

  deleteUser(id: number) {
    return this.http.delete('api/users/' + id);
  }

  getTypeOfLodging() {
    return this.http.get('api/type-lodging');
  }

  addTypeOfLodging(tip: Tip) {
    return this.http.post('api/type-lodging', tip);
  }

  deleteType(id: number) {
    return this.http.delete('api/type-lodging/' + id);
  }

  // ====

  getAdditional() {
    return this.http.get('api/addtional-service');
  }

  addAdditional(tip: Additional) {
    return this.http.post('api/addtional-service', tip);
  }

  deleteAdditional(id: number) {
    return this.http.delete('api/addtional-service/' + id);
  }

  // ====

  getToken(): string {
    return localStorage.getItem('token');
  }

  logout() {
    localStorage.clear();
  }

}
