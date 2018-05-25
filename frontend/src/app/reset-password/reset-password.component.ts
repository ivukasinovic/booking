import { Component, OnInit } from '@angular/core';
import {ChangePasssword} from '../model';
import {UserService} from '../services/user.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrls: ['./reset-password.component.css']
})
export class ResetPasswordComponent implements OnInit {
  chp: ChangePasssword;
  code: string;
  constructor(private userService: UserService, private routeA: ActivatedRoute, private route: Router) {
    this.chp = new ChangePasssword();
  }

  ngOnInit() {
    this.routeA.params.subscribe(params => {
      this.code = params['id'];
    });
  }
  resetPassword() {
    if (this.chp.oldPw !== this.chp.newPw) {
      alert('Passwrods should be same!');
      return;
    }
    this.userService.resetPassword(this.code, this.chp)
      .subscribe(respose => {
        alert('Successfully changed password!');
        this.route.navigate(['/login']);
      },
        error1 => {
        alert('Error, try again!');
        });
  }

}
