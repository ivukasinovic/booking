import { Component, OnInit } from '@angular/core';
import { Lodging, City, Reservation } from '../model';
import {SearchService} from '../services/search.service';
import {Router} from '@angular/router';
import { FormGroup, FormControl, Validators} from '@angular/forms';
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
  });

  lod: Lodging[];
  searchCity: string ;
  searchPersonsNbr: number;
  cities: City[];
  cityName: string;
  reser: Reservation[];
  reserByLodg: Reservation[] = [];
  idLodg: number;
  searchSDT: Date;
  searchEDT: Date;

  constructor(private router: Router, private searchService: SearchService) {
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
    this.searchService.allLodgings().subscribe(
      (response: Lodging[]) => {
        this.lod = response;
      });



  }

  ngOnInit() { }

  onSubmit = function (lodging) {
    console.log(lodging);
    this.searchService.searchLodging(lodging.cityName1, lodging.numberOfPersons1, 'undefined', 'undefined')
      .subscribe(
        (response: Lodging[]) => {
          this.lod = response;
        }
      );
  }
  searchLodging() {
    this.searchService.searchLodging(this.searchCity, this.searchPersonsNbr, this.searchSDT, this.searchEDT)
      .subscribe(
        (response: Lodging[]) => {
          this.lod = response;
        }
      );
  }

  getCityName(br: number): string {
    br = br - 1;
   // alert(br);
    return this.cities[br].name;    // : Observable<User[]>
  }

  getReservationByLodging(br: Lodging) {
   /* const arr: Reservation[] = [];
    this.reser.forEach((item, index) => {
      if (item.lodging === br.id ) { // u idem.lodging se nalazi broj iako ga ond prepoznaje kao Lodging
        arr.push(item);
      }
    });
    return arr;*/
  }



}
