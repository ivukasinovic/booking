import { Component, OnInit } from '@angular/core';
import {Auth} from './model';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  auth: Auth;
  error = '';
  loading = false;
  constructor(private authService: AuthService, private router: Router) {
    this.auth = new Auth();
  }

  ngOnInit() {
  }
  login() {
    this.authService.login(this.auth.username, this.auth.password)
      .subscribe((data: any) => {
        if (data) {
          this.router.navigate(['/reservations']);
          localStorage.setItem('username', data.username)
          alert(data.username);
        }
      }, error1 => {
          alert('Wrong username or password');
        });
  }

}
