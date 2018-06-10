import { Component, OnInit } from '@angular/core';
import {ReserveService} from '../services/reserve.service';
import {UserService} from '../services/user.service';
import {Lodging, Reservation} from '../model';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-my-reservations',
  templateUrl: './my-reservations.component.html',
  styleUrls: ['./my-reservations.component.css']
})
export class MyReservationsComponent implements OnInit {

  reservations: Reservation[] = [];
  lod: Lodging;

  constructor( private userService: UserService, private reserveService: ReserveService) {
    this.userService.getReservationsOfLoggedUser().subscribe(
      (response: Reservation[]) => {
        this.reservations = response;
      });

   /* this.reservations.forEach(element => {
    console.log('smjestaj jee' + element.lodging);
    });*/
  }

  ngOnInit() {

  }


  cancelRes(id: string) {
    this.reserveService.cancel(id).subscribe();
  }

  getLodging(id: string) {
   this.reserveService.getLodging(id).subscribe(
     (response: Lodging) => {
       this.lod = response;
     });
  }

}
