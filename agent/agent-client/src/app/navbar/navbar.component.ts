import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {AuthService} from "../auth/auth.service";



@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  logged = false;
  role: string;
  username: string;
  constructor(private router: Router, private http: HttpClient, private authService: AuthService) {
    this.logged = true;

  }

  ngOnInit() {
    this.role = localStorage.getItem('role');
    this.username = localStorage.getItem('username');
    if (this.authService.isAuthenticated()) {
      this.logged = true;
    } else {
      this.logged = false;
    }
  }
  logOut() {
    // this.authService.logout();
    this.logged = false;
    this.http.get('api/logOut').subscribe();
    localStorage.removeItem('ulogovaniKorisnik');
    localStorage.removeItem('username');
    this.router.navigate(['/']);
  //  window.location.reload();
  }

}
