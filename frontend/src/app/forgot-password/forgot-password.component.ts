import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  email: string;
  constructor(private userService: UserService) { }

  ngOnInit() {
  }
  resetPassword() {
    this.userService.resetPasswordReq(this.email)
      .subscribe(response => {
        alert('Check your email, we sent reset link to you. ');
      },
        error1 => {
        alert('Error occured, maybe your email is incorrect!');
        });
  }

}
