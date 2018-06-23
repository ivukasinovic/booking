import {Component, OnInit} from '@angular/core';
import {ReserveService} from '../services/reserve.service';
import {SearchService} from '../services/search.service';
import {Lodging, Reservation} from '../model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-make-reservation',
  templateUrl: './make-reservation.component.html',
  styleUrls: ['./make-reservation.component.css']
})
export class MakeReservationComponent implements OnInit {

  lodgings: Lodging[];
  avaliable;
  res: Reservation;
  lodging_help: Lodging;
  error = '';

  form = new FormGroup({
    lodging_id: new FormControl(),
    searchSDT: new FormControl(),
    searchEDT: new FormControl()
  });

  constructor(private searchService: SearchService, private reserveService: ReserveService,
              private router: Router, private routeA: ActivatedRoute) {
    this.res = new Reservation();
    this.setTodaysDate();
    this.lodging_help = new Lodging();
  }

  ngOnInit() {
    this.searchService.allLodgings().subscribe(
      (response: Lodging[]) => {
        this.lodgings = response;
      }
    );



    this.routeA.params.subscribe(params => {
      if ( params['id'] != null) {
        this.getLodging(params['id']);
        this.res.dateStart = params['dateS'];
        this.res.dateEnd = params['dateE'];
        this.avaliable = true;
      } else {
        this.avaliable = false;
      }
    });
  }

  getLodging(id: number) {
    this.reserveService.getLodging(id)
      .subscribe((result: Lodging) => {
         this.lodging_help = result;
        },
        error1 => {
          alert('Doslo je do greske');
        });
  }
  checkAvaliability() {
    if (this.form.value.searchSDT >= this.form.value.searchEDT) {
      this.error = 'Strat date must be lower than the end date !';
      return;
    }
    if ( !this.form.value.lodging_id
      || !this.form.value.searchSDT
      || !this.form.value.searchEDT) {
      this.error = 'Please select lodging and duration!';
      return;
    }

    this.reserveService.checkAvaliability( this.form.value.lodging_id , this.form.value.searchSDT, this.form.value.searchEDT)
      .subscribe( (res: Response) => {
        this.error = 'Accommodation is available. You can reserve.';
        this.avaliable = true;
      } ,
        error => {
          this.error = 'Accommodation is reserved in selected period';
          this.avaliable = false;
        });

  }

  onSubmit = function () {
    if (this.form.value.searchSDT >= this.form.value.searchEDT) {
      this.error = 'Strat date must be lower than the end date !';
      return;
    }
    this.res.dateStart =  this.form.value.searchSDT;
    this.res.dateEnd = this.form.value.searchEDT;
    this.reserveService.reserve(this.res, this.form.value.lodging_id).subscribe((res: Response) => {
        alert('Your reservation is accepted. You can see details on your profile.');
        this.router.navigateByUrl('/');
      });
  };

  resetAvaliable() {
    this.avaliable = false;
  }


  setTodaysDate() {
    const today = new Date();
    const dd = today.getDate();
    const mm = today.getMonth() + 1; // January is 0!
    const yyyy = today.getFullYear();
    let pd = dd.toString();
    let pm = mm.toString();
    if ( dd < 10 ) {
      pd = '0' + dd;
    }
    if (mm < 10) {
      pm = '0' + mm ;
    }
    const todayStr = yyyy + '-' + pm + '-' + pd ;

    this.res.dateStart = new Date(todayStr);
    this.res.dateEnd = new Date(todayStr);

  }
}
