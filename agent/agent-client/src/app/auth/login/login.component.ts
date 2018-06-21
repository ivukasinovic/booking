import { Component, OnInit } from '@angular/core';
import {Auth} from './model';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  auth: Auth;
  error = '';
  loading = false;
  constructor(private authService: AuthService, private router: Router , private http: HttpClient) {
    this.auth = new Auth();

  }

  ngOnInit() {
  }

  login(username: string, lozinka: string) {
    return this.http.post('api/login', {username: username, password: lozinka})
      .subscribe(res => {
          localStorage.setItem('ulogovaniKorisnik', JSON.stringify(res));
          localStorage.setItem('username', username);
          this.router.navigate(['/lodging-list']);
          window.location.reload();
        },
        err => {
          alert('Pogresan username ili lozinka!');
          console.log(err);
        });
  }

  logOut() {
    this.http.get('api/logOut').subscribe();
    localStorage.removeItem('ulogovaniKorisnik');

  }


}
