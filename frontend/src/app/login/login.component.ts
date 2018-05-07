import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import * as decode from 'jwt-decode';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  providers: [AuthService]
})
export class LoginComponent implements OnInit {
  token: string;
  model: any = {};
  error = '';
  loading = false;

  constructor(private router: Router, private authenticationService: AuthService) {
    const token = localStorage.getItem('token');
    this.token =  token;
  }

  ngOnInit() {
  }

  login() {
    this.loading = true;
    this.authenticationService.login(this.model.username, this.model.password)
      .subscribe(
        (data: any) => {
          if (data) {
            this.token = data.token;
            localStorage.setItem('token', this.token);
            const tokenPayload = decode(this.token);
            localStorage.setItem('role', tokenPayload.role);
            localStorage.setItem('username', tokenPayload.sub);
            window.location.reload();
            this.router.navigate(['/']);
          }
        },
        error => {
          this.error = 'Pogresno korisnicko ime ili lozinka';
          this.loading = false;
        }
      );
  }
}
