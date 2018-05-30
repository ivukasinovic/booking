import {Component, OnInit} from '@angular/core';
import {AuthService} from '../auth.service';
import {Router} from '@angular/router';
import {Comment, User} from '../models/user';
import {log} from 'util';
import {headersToString} from 'selenium-webdriver/http';
import {ContentType} from '@angular/http/src/enums';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrls: ['./comment.component.css']
})
export class CommentComponent implements OnInit {

  komentari: Comment[];
  users: User[];

  constructor(private authService: AuthService, private router: Router) {
    this.authService.getUsers().subscribe(
      (response: User[]) => {
        this.users = response;
      });
    this.authService.allComments().subscribe(
      (response: Comment[]) => {
        this.komentari = response;
      });   // err kad stavim ispise mi gresku

  }

  ngOnInit() {
  }

  vratiIme(br: number): string {
    br = br - 1;
    return this.users[br].name;    // : Observable<User[]>
  }

  obrisi(br: number) {
    console.log('Uslo: brisi ' + ' __ ' + br);
    // headersToString(ContentType.JSON);
    this.authService.deleteComment(br)                 // Treba promeniti !!!!
      .subscribe(response => {
          alert('Uspesno ste obrisali komentar!!!');
          window.location.reload();
        },
        err => {
          alert('Niste uspeli obrisati komentar (Doslo je do greske)');
        });

  }

  potvrdi(br: number) {
    console.log('Uslo: ' + ' __ ' + br);
    //  this.komentari[br].accepted = true;
    this.authService.prihvaticOMMENT(br)
      .subscribe(response => {
          alert('Uspesno ste prihvatili komentar i sada se ne nalazi u listi ne odobrenih komentara !!!');
          window.location.reload();
        },
        err => {
          alert('Neuspesna potvrda komentara');
        });
  }

}
