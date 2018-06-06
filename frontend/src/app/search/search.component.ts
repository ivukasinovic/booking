import { Component, OnInit } from '@angular/core';
import { Lodging, City, Reservation } from '../model';
import {SearchService} from '../services/search.service';
import {Router} from '@angular/router';
import {FormGroup, FormControl, Validators, AbstractControl} from '@angular/forms';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  searchFormGroup: FormGroup;
  form = new FormGroup({
    cityName1: new FormControl('', Validators.compose ([Validators.required])),
    numberOfPersons1: new FormControl('', Validators.compose ([Validators.required])),
    searchSDT: new FormControl('2018-06-06'),
    searchEDT: new FormControl('2018-06-06')
  });
  lod: Lodging[];
  searchCity: string ;
  searchPersonsNbr: number;
  res: Reservation;
  cities: City[];
  cityName: string;
  reser: Reservation[];
  reserByLodg: Reservation[] = [];
  idLodg: number;
  searchSDT: Date;
  searchEDT: Date;
  constructor(private router: Router, private searchService: SearchService) {
    this.res = new Reservation();
    this.searchService.getReservations().subscribe(
      (response: Reservation[]) => {
        console.log('Prosao konstruktor');
        this.reser = response;
      });
    this.searchService.getCities().subscribe(
      (response: City[]) => {
        console.log('!!!Prosao konstruktor');
        this.cities = response;
      });   // err kad stavim ispise
    /*
    this.searchService.allLodgings().subscribe(
      (response: Lodging[]) => {
        this.lod = response;
      });
*/
  }

  ngOnInit() { }

  onSubmit = function (lodging) {
    console.log(lodging);
    this.searchService.searchLodging(lodging.cityName1, lodging.numberOfPersons1, lodging.searchSDT, lodging.searchEDT)
      .subscribe(
        (response: Lodging[]) => {
          this.lod = response;
        }
      );
  };

  getCityName(br: number): string {
    br = br - 1;
   // alert(br);
    return this.cities[br].name;    // : Observable<User[]>
  }



}
