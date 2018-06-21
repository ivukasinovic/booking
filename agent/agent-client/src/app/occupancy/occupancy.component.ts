import { Component, OnInit } from '@angular/core';
import {Lodging, Reservation} from '../model';
import {LodgingService} from '../lodging.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-occupancy',
  templateUrl: './occupancy.component.html',
  styleUrls: ['./occupancy.component.css']
})
export class OccupancyComponent implements OnInit {
  reservation: Reservation;
  constructor(private lodgingService: LodgingService, private router: Router) {
    this.reservation = new Reservation();
  }

  ngOnInit() {
  }
  reserve() {
    this.lodgingService.reserve(this.reservation)
      .subscribe(() => {
        alert('Success!');
        this.router.navigate(['/']);
      }, error1 => {
        alert('Error, try different dates!');
     //   location.reload();
      });
  }

}
