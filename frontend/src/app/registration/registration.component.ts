import { Component, OnInit } from '@angular/core';
import {User} from '../model';
import {AuthService} from '../services/auth.service';
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
          this.passwordValueValidator(data.passwordHash);
        this.router.navigate(['/']);
        },
        error1 => {
        alert('Error!');
        }
      );
  }

  // Both capital letters, small letters, numbers and special characters
  // /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{6,100})/

  passwordValueValidator(control) {
    if (control.value !== undefined) {
      if (!control.value.match(/^(?=.*[0-9])[a-zA-Z0-9!@#$%^&*]{6,100}$/)) {      // broj i slova
        return { 'invalidPassword': true };
      } else {
        // here i need to add check for special characters
      }
    }
  }

}
