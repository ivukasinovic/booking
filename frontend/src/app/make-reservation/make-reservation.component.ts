import { Component, OnInit } from '@angular/core';
import {ReserveService} from '../services/reserve.service';
import {SearchService} from '../services/search.service';
import {Lodging, Reservation} from '../model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-make-reservation',
  templateUrl: './make-reservation.component.html',
  styleUrls: ['./make-reservation.component.css']
})
export class MakeReservationComponent implements OnInit {

  lodgings: Lodging[];
  avaliable = false;
  res: Reservation;

  form = new FormGroup({
    lodging_id: new FormControl(),
    searchSDT: new FormControl('2018-06-06'),
    searchEDT: new FormControl('2018-06-06')
  });
  constructor(private searchService: SearchService, private reserveService: ReserveService, private router: Router) {
    this.res = new Reservation();
  }

  ngOnInit() {
    this.searchService.allLodgings().subscribe(
      (response: Lodging[]) => {
        this.lodgings = response;
      }
    );
  }

  checkAvaliability(reservation) {
    if (reservation.lodging_id === null) {
      console.log('select lodging');
      return;
    }

    this.reserveService.checkAvaliability( reservation.lodging_id , reservation.searchSDT, reservation.searchEDT)
      .subscribe( (res: Response) => {
      this.avaliable = true;
      } ,
        error => {
          this.avaliable = false;
        });

  }

  onSubmit = function (reservation) {
    this.res.dateStart = reservation.searchSDT;
    this.res.dateEnd = reservation.searchEDT;
    this.reserveService.reserve(this.res, reservation.lodging_id).subscribe((res: Response) => {
        this.router.navigateByUrl('/');
      });
  };

}
