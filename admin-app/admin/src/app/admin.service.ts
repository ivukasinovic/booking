import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient, private router: Router) {
  }

  noviTip(tip: string) {
    return this.http.post('api/novi-tip', tip);
  }

}
