import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../services/user.service';
import {ChangePasssword} from '../model';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  chp: ChangePasssword;
  oldPw: string;
  newPw1: string;
  newPw2: string;
  constructor(private router: Router, private userService: UserService){
    this.chp = new ChangePasssword();
  }

  ngOnInit() {
  }
  changePassword() {
    if (this.newPw1 !== this.newPw2) {
      alert('Passwrods should be same!');
      return;
    }
    this.chp.newPw = this.newPw1;
    this.chp.oldPw = this.oldPw;
    this.userService.changePassword(this.chp)
      .subscribe(response => {
         alert('Succesfully changed');
         this.router.navigate(['/']);
        },
        err => {
          alert('Nije uspela promena lozinke, proverite polja');
        });
  }
}
