import { Component, OnInit } from '@angular/core';
import {Lodging, City, Reservation, AditionalServices, TypeOfLodging, PriceList} from '../model';
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
  typeLod: TypeOfLodging [];
  nizCekiranih: number[];
  cekiraniSlanje: number[];
  aditionServices: AditionalServices [];
  lod: Lodging[];
  res: Reservation;
  cities: City[];
  reser: Reservation[];
  priceList: PriceList[];
  priceListLodging: PriceList[];
  form = new FormGroup({
    cityName1: new FormControl('', Validators.compose ([Validators.required])),
    numberOfPersons1: new FormControl('', Validators.compose ([Validators.required])),
    searchSDT: new FormControl('2018-06-06'),
    searchEDT: new FormControl('2018-06-06'),
    typeOfLodging: new FormControl('undefined'),


  });

  constructor(private router: Router, private searchService: SearchService) {
    this.nizCekiranih = [];
    this.cekiraniSlanje = [];
    this.res = new Reservation();
    this.priceList = [];
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
      this.aditionServices = response;
    });

    this.searchService.getAllTypeOfLodging().subscribe((response: TypeOfLodging[]) => {
      this.typeLod = response;
    });
    this.searchService.getAllPriceList().subscribe((response: PriceList[]) => {
      this.priceList = response;
    });
  }
  onSubmit = function (lodging, aditionS) {
    console.log(lodging);
    console.log(aditionS);

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
  getPriceListByLodging(lodId: number): string {
    this.priceListLodging = [];
    for (let i = 0 ; i < this.priceList.length ; i++ ) {
      if (this.priceList[i].lodging === lodId) {
       // console.log('OOOOOOOO 22' + this.form.value.searchSDT.toString().slice(0, -6) + ' i ' + this.priceList[i].year +
         // 'mmesec' + this.form.value.searchSDT.toString().slice(5, -3));
        console.log(this.priceList[i]);
        if (this.priceList[i].year === this.form.value.searchSDT.toString().slice(0, -6)) { // uzmem  godinu
          if (this.form.value.searchSDT.toString().slice(5, -3) === '01') {
            return this.priceList[i].january.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '02') {
            return this.priceList[i].february.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '03') {
            return this.priceList[i].mart.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '04') {
            return this.priceList[i].april.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '05') {
            return this.priceList[i].may.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '06') {
            return this.priceList[i].june.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '07') {
            return this.priceList[i].july.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '08') {
            return this.priceList[i].august.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '09') {
            return this.priceList[i].september.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '10') {
            return this.priceList[i].october.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '11') {
            return this.priceList[i].november.toString();
          } else if (this.form.value.searchSDT.toString().slice(5, -3) === '12') {
            return this.priceList[i].december.toString();
          } else {
            return 'No price yet'; }
        } else { return 'No price yet'; }
      }
    }
    return 'No price yet';
  }

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

  sortByPrice(priceLod: Lodging[]){

   // var sortedArray: number[] = numericArray.sort((n1,n2) => n1 - n2);
  }
}
