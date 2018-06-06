import { Component, OnInit } from '@angular/core';
import {LodgingService} from '../lodging.service';
import {Reservation} from '../model';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css']
})
export class ReservationListComponent implements OnInit {
  reservations: Reservation[];
  constructor(private lodgingService: LodgingService) { }

  ngOnInit() {
      this.lodgingService.getReservations()
        .subscribe((response: Reservation[])  => {
            this.reservations = response;
        });
  }
  setCompleted(id: number) {
    this.lodgingService.setCompleted(id)
      .subscribe();
    location.reload();
  }

}
