import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {User} from '../models/user';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  users: User[];
  usersPrim: User[];

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
    this.authService.getUsersSamo().subscribe(
      (response: User[]) => {
        this.users = response;
      });

    this.authService.getUsers().subscribe(
      (response: User[]) => {
        this.usersPrim = response;
      });


  }

  activate(br: number) {
    this.authService.activate(br)
      .subscribe(response => {
          alert('Uspesno ste aktivirali!!!');
          window.location.reload();
        },
        err => {
          alert('Niste uspeli (Doslo je do greske)');
        });
  }

  block(br: number) {
    this.authService.block(br)
      .subscribe(response => {
          alert('Deaktivirali ste !!!');
          window.location.reload();
        },
        err => {
          alert('Niste uspeli obrisati (Doslo je do greske)');
        });
  }

  obrisii(br: number) {
    this.authService.deleteUser(br)
      .subscribe(response => {
          alert('Uspesno ste obrisali korisnika!!!');
          window.location.reload();
        },
        err => {
          alert('Niste uspeli obrisati (Doslo je do greske)');
        });

  }


}
