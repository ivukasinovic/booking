import { Component, OnInit } from '@angular/core';
import {Auth} from './model';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  auth: Auth;
  error = '';
  loading = false;
  constructor(private authService: AuthService) {
    this.auth = new Auth();
  }

  ngOnInit() {
  }
  login() {
    this.authService.login(this.auth.username, this.auth.password)
      .subscribe();
  }

}
