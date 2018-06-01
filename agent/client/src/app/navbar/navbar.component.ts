import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  logged = false;
  role: string;
  username: string;
  constructor(private router: Router) { }

  ngOnInit() {
    this.role = localStorage.getItem('role');
    this.username = localStorage.getItem('username');
    // if (this.authService.isAuthenticated()) {
    //   this.logged = true;
    // } else {
    //   this.logged = false;
    // }
  }
  logout() {
    // this.authService.logout();
    this.logged = false;
    this.router.navigate(['/login']);
    window.location.reload();
  }

}
