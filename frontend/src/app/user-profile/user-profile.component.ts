import { Component, OnInit } from '@angular/core';
import {Lodging, Reservation} from '../model';
import {UserService} from '../services/user.service';
import {ReserveService} from "../services/reserve.service";

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  reservations: Reservation[] = [];

  constructor( private userService: UserService, private reserveService: ReserveService) {
    this.userService.getReservationsOfLoggedUser().subscribe(
      (response: Reservation[]) => {
        this.reservations = response;
      });
  }

  ngOnInit() {
  }

  cancelRes(id: string){
    this.reserveService.cancel(id).subscribe();
  }

}
