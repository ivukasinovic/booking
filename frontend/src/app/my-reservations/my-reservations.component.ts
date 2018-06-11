import { Component, OnInit } from '@angular/core';
import {ReserveService} from '../services/reserve.service';
import {UserService} from '../services/user.service';
import {Lodging, Reservation} from '../model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {

  reservations: Reservation[] = [];
  _ref: any;
  constructor( private userService: UserService, private reserveService: ReserveService, private router: Router) {}

  ngOnInit() {
    this.userService.getReservationsOfLoggedUser().subscribe(
      (response: Reservation[]) => {
        this.reservations = response;
        this.reservations.forEach(element => {
         this.reserveService.getLodging(element.lodging).subscribe(
           (resp: Lodging) => {
             element.lodging = resp;
           }
         );
        });

      });


  }


  cancelRes(id: string) {
    this.reserveService.cancel(id).subscribe((res: Response) => {
       document.getElementById(id).remove();
    });

  }

  sendMessage(id: string) {
    localStorage.setItem('res-id', id);
      this.router.navigateByUrl('/send-message');
  }


}
