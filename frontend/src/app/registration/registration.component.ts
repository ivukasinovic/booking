import { Component, OnInit } from '@angular/core';
import {User} from '../model';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: User;
  constructor(private authService: AuthService, private router: Router) {
    this.user = new User();
  }

  ngOnInit() {
  }
  register() {
    this.authService.register(this.user)
      .subscribe((data: User) => {
        alert('Succes registration ' + data.username + '!');
        this.router.navigate(['/']);
        },
        error1 => {
        alert('Error!');
        }
      );
  }
}
