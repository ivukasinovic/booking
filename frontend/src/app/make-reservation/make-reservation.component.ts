import {Component, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ReserveService} from '../services/reserve.service';
import {SearchService} from '../services/search.service';
import {Lodging, Reservation} from '../model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {Certificate} from '../certificates/model';
import any = jasmine.any;

@Component({
  selector: 'app-make-reservation',
  templateUrl: './make-reservation.component.html',
  styleUrls: ['./make-reservation.component.css']
})
export class MakeReservationComponent implements OnInit {

  lodgings: Lodging[];
  avaliable = false;
  res: Reservation;
  lodging_help: Lodging;


  form = new FormGroup({
    lodging_id: new FormControl(),
    searchSDT: new FormControl('2018-06-06'),
    searchEDT: new FormControl('2018-06-06')
  });

  constructor(private searchService: SearchService, private reserveService: ReserveService,
              private router: Router, private routeA: ActivatedRoute) {
    this.res = new Reservation();
  this.lodging_help = new Lodging();
  }

  ngOnInit() {
    this.searchService.allLodgings().subscribe(
      (response: Lodging[]) => {
        this.lodgings = response;
      }
    );



    this.routeA.params.subscribe(params => {
      console.log(' parametar je ' + params['id']);

      if ( params['id'] != null) {

        this.getLodging(params['id']);
        this.res.dateStart = params['dateS'];
        this.res.dateEnd = params['dateE'];
        this.avaliable = true;
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
    if (this.form.value.lodging_id === null) {
      return;
    }

    this.reserveService.checkAvaliability( this.form.value.lodging_id , this.form.value.searchSDT, this.form.value.searchEDT)
      .subscribe( (res: Response) => {
      this.avaliable = true;
      } ,
        error => {
          this.avaliable = false;
        });

  }

  onSubmit = function () {
    console.log('rezervacija je ', this.form.value.lodging_id, this.res.dateStart, this.res.dateEnd);
    this.res.dateStart =  this.form.value.searchSDT;
    this.res.dateEnd = this.form.value.searchEDT;
    this.reserveService.reserve(this.res, this.form.value.lodging_id).subscribe((res: Response) => {
        this.router.navigateByUrl('/');
      });
  };

  resetAvaliable() {
    this.avaliable = false;
  }

}
