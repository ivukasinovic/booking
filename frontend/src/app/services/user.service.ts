import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ChangePasssword} from '../model';

@Injectable()
export class UserService {
  chp: {
    oldPw: '',
    newPw: ''
  }
  constructor(private http: HttpClient) { }

  changePassword(chp: ChangePasssword) {
    return this.http.post('api/users/change-password', chp);
  }
}
