import { Component, OnInit } from '@angular/core';
import {User} from '../model';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  user: User;
  constructor(private authService: AuthService) {
    this.user = new User();
  }

  ngOnInit() {
  }
  register() {
    this.authService.register(this.user)
      .subscribe();
  }
}
