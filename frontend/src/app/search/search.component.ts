import { Component, OnInit } from '@angular/core';
import {Lodging, City, Reservation, AditionalServices} from '../model';
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
  adition: AditionalServices;

  nizCekiranih: number[];
  cekiraniSlanje: number[];
  aditionServices: AditionalServices [];
  form = new FormGroup({
    cityName1: new FormControl('', Validators.compose ([Validators.required])),
    numberOfPersons1: new FormControl('', Validators.compose ([Validators.required])),
    searchSDT: new FormControl('2018-06-06'),
    searchEDT: new FormControl('2018-06-06'),
    typeOfLodging: new FormControl('undefined'),


  });
  lod: Lodging[];
  res: Reservation;
  cities: City[];
  reser: Reservation[];
  constructor(private router: Router, private searchService: SearchService) {
    this.nizCekiranih = [];
    this.cekiraniSlanje = [];
    this.res = new Reservation();
    this.searchService.getReservations().subscribe(
      (response: Reservation[]) => {

        this.reser = response;
      });
    this.searchService.getCities().subscribe(
      (response: City[]) => {

        this.cities = response;
      });   // err kad stavim ispise
    /*
    this.searchService.allLodgings().subscribe(
      (response: Lodging[]) => {
        this.lod = response;
      });
*/
  }

  ngOnInit() {
    this.searchService.getAllAditionalServices().subscribe((response: AditionalServices[]) => {
      console.log('Prosao init');
      this.aditionServices = response;
    });
  }
  onSubmit = function (lodging, aditionS) {
    console.log(lodging);
    console.log(aditionS);
/*
    for (let i = 0 ; i < this.nizCekiranih.length ; i++ ) {
      let pom = 0;
      for (let k = 0 ; k < this.nizCekiranih.length ; k++ ) {
        console.log('Cek [i]' + this.nizCekiranih[i]  + ' i Cek[k]' +  this.nizCekiranih[k] );
        if (this.nizCekiranih[i] === this.nizCekiranih[k]) {
          pom = pom + 1;
          console.log('nasao');
        }
      }
      console.log('pom za ' + pom + ' zza' + this.nizCekiranih[i] );
      if (pom % 2 === 1) {
        console.log('Ubacujem za slanje ' + this.nizCekiranih[i]   );
        this.cekiraniSlanje.push(this.nizCekiranih[i]);
      }
    }
    */
    console.log('Cekiran niz ' + this.nizCekiranih);
    console.log('Cekiran Slanje' + this.cekiraniSlanje);
    this.searchService.searchLodging(lodging.cityName1, lodging.numberOfPersons1, lodging.searchSDT,
      lodging.searchEDT, lodging.typeOfLodging, this.nizCekiranih)
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

  vrati(id: number) {
    const text = document.getElementById('text');
    console.log('check');
    const element: HTMLInputElement =  <HTMLInputElement>document.getElementById(id.toString());
    const isChecked = element.checked;
    if (element.checked === true) {
    //  text.style.display = 'block';

      this.nizCekiranih.push(id);

    } else {
        for (let i = 0 ; i < this.nizCekiranih.length ; i++ ) {
        if (this.nizCekiranih[i] === id ) {
          console.log('brisem iz liste ' + id + ' na poziciji ' + i);
          this.nizCekiranih.splice(i, 1);
          console.log('duzina liste ' + this.nizCekiranih.length);
        }
      }
    }

  }
}
