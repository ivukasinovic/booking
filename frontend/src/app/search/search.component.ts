import { Component, OnInit } from '@angular/core';
import {Lodging, City, Reservation, AditionalServices, TypeOfLodging, PriceList, CategoryOfLodging, Comment, User} from '../model';
import {SearchService} from '../services/search.service';
import {Router} from '@angular/router';
import {FormGroup, FormControl, Validators, AbstractControl} from '@angular/forms';
import {ReserveService} from '../services/reserve.service';
import {AuthService} from '../services/auth.service';
import {concat} from 'rxjs/operators';

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
  com: Comment[];
  comForLod: Comment[];
  user: User[];

  form = new FormGroup({
    cityName1: new FormControl('', Validators.compose ([Validators.required])),
    numberOfPersons1: new FormControl('', Validators.compose ([Validators.required])),
    searchSDT: new FormControl(this.getTodaysDate(), Validators.compose ([Validators.maxLength(10), this.dateValidationStart]) ),
    searchEDT: new FormControl(this.getTodaysDate(), Validators.compose ([Validators.maxLength(10),
      this.dateValidationStart]) ), // god-mes-dan
    typeOfLodging: new FormControl('undefined'),
    rating: new FormControl(''),
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
    this.com = [];
    this.comForLod = [];
    this.user = [];
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
    this.searchService.getAllComment().subscribe((response: Comment[]) => {
      this.com = response;
    });
    this.searchService.getAllUsers().subscribe((response: User[]) => {
      this.user = response;
    });
  }

  onSubmit = function (lodging, aditionS) {
    if (lodging.rating === '') {
      lodging.rating = 'undefined';
    }
  if (lodging.searchSDT <= lodging.searchEDT) {
    this.searchService.searchLodging(lodging.cityName1, lodging.numberOfPersons1, lodging.searchSDT,
      lodging.searchEDT, lodging.typeOfLodging, lodging.categoryOfLodging, lodging.rating, this.nizCekiranih)
      .subscribe(
        (response: Lodging[]) => {
          this.lod = response;
        }
      );
  } else {
    alert('Strat date must be lower than the end date !');
  }
  };

dateValidationStart(control) {
  const today = new Date();
  const startD = new Date(control.value);
  if ((+control.value.slice(0, -6)) < (+today.getFullYear())) {// ako je  godina uneta < manja od danasnje - ne moze
    return {'searchSDT': false};
  }
  if ((+control.value.slice(0, -6)) === (+today.getFullYear())) {
   // console.log(control.value.slice(5, -3) + '  i  ' + (today.getMonth() + 1));
    if (+control.value.slice(5, -3) < (+(today.getMonth() + 1))) {// da li je u istoj godini mesec uneti manji od danasnjeg - ne moze
      console.log('mesec nije dobar');
      return {'searchSDT': false};
    }
    if (+control.value.slice(5, -3) === (+(today.getMonth() + 1))) {// ako je mesec isti proveri dan
      if (+control.value.slice(8, 10) < (+(today.getDate()))) {
      console.log('dan nije dobar');
      return {'searchSDT': false};
      }
    }
  }
}

getTodaysDate(): string {
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
  return todayStr;
}

getPriceListByLodging(lodId: number): string {
    for (let i = 0 ; i < this.priceList.length ; i++ ) {
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

  sortByPrice2(priceLod: Lodging[], asc: boolean) { // nema potrebe prosledjivati

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
        if (asc) {
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

  sortByCategory(priceLod: Lodging[], asc: boolean) {
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
        if (asc) {
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


  sortByRating(priceLod: Lodging[], asc: boolean) {
    priceLod = this.lod;
    if (asc) {
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
    this.res.dateStart = this.form.value.searchSDT;
    this.res.dateEnd = this.form.value.searchEDT;

    if (this.authService.isAuthenticated()) {
      this.router.navigateByUrl('/make-reservation/' + id + '/' + this.res.dateStart + '/' + this.res.dateEnd);
    } else {
      localStorage.setItem('reserve', 'true');
      localStorage.setItem('make-res-path', '/make-reservation/' + id + '/' + this.res.dateStart + '/' + this.res.dateEnd);
      console.log('/make-reservation/' + id + '/' + this.res.dateStart + '/' + this.res.dateEnd);
      this.router.navigateByUrl('/login');
    }
  }
  getCommentForLod(id: number) {
    this.comForLod = [];
    for (let i = 0; i < this.com.length; i++) {
      if (this.com[i].lodging === id) {
        this.comForLod.push(this.com[i]);

      }
    }
    return this.comForLod;
  }

  getUserName(userId: number): string {
  for (let i = 0; i < this.user.length; i++) {
    if (this.user[i].id === userId) {
      return this.user[i].username;
    }
  }
  }
}

