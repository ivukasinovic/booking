import { Component, OnInit } from '@angular/core';
import {Lodging, City, Reservation, AditionalServices, TypeOfLodging, PriceList, CategoryOfLodging} from '../model';
import {SearchService} from '../services/search.service';
import {Router} from '@angular/router';
import {FormGroup, FormControl, Validators, AbstractControl} from '@angular/forms';
import {ReserveService} from '../services/reserve.service';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})


export class SearchComponent implements OnInit {
  searchFormGroup: FormGroup;
  clickedPrice: boolean;
  clickedCategory: boolean;
  clickedRating: boolean;
  typeLod: TypeOfLodging [];
  catLod: CategoryOfLodging[];
  nizCekiranih: number[];
  listLodId: number[];
  cekiraniSlanje: number[];
  aditionServices: AditionalServices [];
  lod: Lodging[];
  res: Reservation;
  cities: City[];
  reser: Reservation[];
  priceList: PriceList[];
  lodSort: Lodging[];
  form = new FormGroup({
    cityName1: new FormControl('', Validators.compose([Validators.required])),
    numberOfPersons1: new FormControl('', Validators.compose([Validators.required])),
    searchSDT: new FormControl('2018-06-06'),
    searchEDT: new FormControl('2018-06-06'),
    typeOfLodging: new FormControl('undefined'),
    categoryOfLodging: new FormControl('undefined')
  });


  constructor(private router: Router, private searchService: SearchService, private reservationService: ReserveService,
              private authService: AuthService) {
    this.clickedPrice = false;
    this.clickedCategory = false;
    this.clickedRating = false;
    this.listLodId = [];
    this.nizCekiranih = [];
    this.cekiraniSlanje = [];
    this.res = new Reservation();
    this.lodSort = [];
    this.lod = [];
    this.priceList = [];
    this.searchService.getReservations().subscribe(
      (response: Reservation[]) => {

        this.reser = response;
      });
    this.searchService.getCities().subscribe(
      (response: City[]) => {

        this.cities = response;
      });   // err kad stavim ispise

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
    this.searchService.getAllCategoryOfLodging().subscribe((response: CategoryOfLodging[]) => {
      this.catLod = response;
      for (let i = 0; i < this.catLod.length; i++) {
        if (this.catLod[i].label === '0') {
          this.catLod[i].label = 'uncategorized';
        }
      }
    });
  }

  onSubmit = function (lodging, aditionS) {
    this.searchService.searchLodging(lodging.cityName1, lodging.numberOfPersons1, lodging.searchSDT,
      lodging.searchEDT, lodging.typeOfLodging, lodging.categoryOfLodging, this.nizCekiranih)
      .subscribe(
        (response: Lodging[]) => {
          this.lod = response;
        }
      );
  };


  getPriceListByLodging(lodId: number): string {
    for (let i = 0; i < this.priceList.length; i++) {
      if (this.priceList[i].lodging === lodId) {
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
            return 'No price yet';
          }
        } else {
          return 'No price yet';
        }
      }
    }
    return 'No price yet';
  }

  getCityName(br: number): string {
    br = br - 1;
    // alert(br);
    return this.cities[br].name;    // : Observable<User[]>
  }


  getCategoryLabel(id: string): string {

    for (let i = 0; i < this.catLod.length; i++) {
      if (this.catLod[i].id === +id) {
        if (this.catLod[i].label === '0') {
          return 'uncategorized';
        }
        return this.catLod[i].label;
      }
    }

    return 'error';
  }

  vrati(id: number) {
    const text = document.getElementById('text');
    console.log('check');
    const element: HTMLInputElement = <HTMLInputElement>document.getElementById(id.toString());
    const isChecked = element.checked;
    if (element.checked === true) {
      //  text.style.display = 'block';

      this.nizCekiranih.push(id);

    } else {
      for (let i = 0; i < this.nizCekiranih.length; i++) {
        if (this.nizCekiranih[i] === id) {
          console.log('brisem iz liste ' + id + ' na poziciji ' + i);
          this.nizCekiranih.splice(i, 1);
          console.log('duzina liste ' + this.nizCekiranih.length);
        }
      }
    }

  }

  sortByPrice2(priceLod: Lodging[]) { // nema potrebe prosledjivati

    for (let i = 0; i < priceLod.length - 1; i++) {

      for (let j = 0; j < priceLod.length - i - 1; j++) {
        const priceStrI = (this.getPriceListByLodging(priceLod[j + 1].id)); // stavljamo cene u listu na osnovu pretrazenih smestaja
        const priceStrJ = (this.getPriceListByLodging(priceLod[j].id));
        let priceNumbI = 0;
        let priceNumbJ = 0;
        if (priceStrI === 'No price yet') {
          priceNumbI = 0;
        } else {
          priceNumbI = +priceStrI;
        }
        if (priceStrJ === 'No price yet') {
          priceNumbJ = 0;
        } else {
          priceNumbJ = +priceStrJ;
        }
        if (!this.clickedPrice) {
          if (priceNumbJ > priceNumbI) {
            const pom = priceLod[j];
            priceLod[j] = priceLod[j + 1];
            priceLod[j + 1] = pom;
          }
        } else {
          if (priceNumbJ < priceNumbI) {
            const pom = priceLod[j];
            priceLod[j] = priceLod[j + 1];
            priceLod[j + 1] = pom;
          }
        }
      }
    }
    if (this.clickedPrice) {
      this.clickedPrice = false;
    } else {
      this.clickedPrice = true;
    }
    this.lod = priceLod;
  }

  sortByCategory(priceLod: Lodging[]) {
    for (let i = 0; i < priceLod.length - 1; i++) {

      for (let j = 0; j < priceLod.length - i - 1; j++) {
        const priceStrI = this.getCategoryLabel(priceLod[j + 1].category); // stavljamo cene u listu na osnovu pretrazenih smestaja
        const priceStrJ = (this.getCategoryLabel(priceLod[j].category));
        let priceNumbI = 0;
        let priceNumbJ = 0;
        if (priceStrI === 'uncategorized') {
          priceNumbI = 0;
        } else {
          priceNumbI = +priceStrI;
        }
        if (priceStrJ === 'uncategorized') {
          priceNumbJ = 0;
        } else {
          priceNumbJ = +priceStrJ;
        }
        if (!this.clickedCategory) {
          if (priceNumbJ < priceNumbI) {
            const pom = priceLod[j];
            priceLod[j] = priceLod[j + 1];
            priceLod[j + 1] = pom;
          }
        } else {
          if (priceNumbJ > priceNumbI) {
            const pom = priceLod[j];
            priceLod[j] = priceLod[j + 1];
            priceLod[j + 1] = pom;
          }
        }
      }
    }
    if (this.clickedCategory) {
      this.clickedCategory = false;
    } else {
      this.clickedCategory = true;
    }
    this.lod = priceLod;
  }


  sortByRating(priceLod: Lodging[]) {
    priceLod = this.lod;
    if (!this.clickedRating) {
      this.lod = priceLod.sort(this.algoritamCeneSort);
    } else {
      this.lod = priceLod.sort(this.algoritamCeneSort2);
    }
    if (this.clickedRating) {
      this.clickedRating = false;
    } else {
      this.clickedRating = true;
    }
  }

  algoritamCeneSort(l1: Lodging, l2: Lodging) {
    return (l2.rating) - (l1.rating);
  }

  algoritamCeneSort2(l1: Lodging, l2: Lodging) {
    return (l1.rating) - (l2.rating);
  }

  reserve(id: string) {
    if (this.authService.isAuthenticated()) {
      this.res.dateStart = this.form.value.searchSDT;
      this.res.dateEnd = this.form.value.searchEDT;
      this.reservationService.reserve(this.res, id).subscribe();
    } else {
      this.router.navigateByUrl('/login');
    }
  }
}

