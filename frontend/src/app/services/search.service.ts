import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Lodging, TypeOfLodging, AditionalServices} from '../model';

@Injectable()
export class SearchService {

  constructor(private router: Router, private http: HttpClient) { }

  allLodgings() {
    return this.http.get('api/lodging/getLodgings');
  }
  searchLodging(cityName: string, nbrOfPersons: number, dateStart: Date, dateEnd: Date,
                typeOfLodging: string, catOfLodging: string, rating: string, as: AditionalServices) {
    return this.http.post('api/search/searchLodging/' + cityName + '/' + nbrOfPersons +
      '/' + dateStart + '/' + dateEnd + '/' + typeOfLodging + '/' + '/' + catOfLodging + '/' + rating + '/', as );
  }

  getAllAditionalServices() {
    return this.http.get('api/addtional-service');
  }

  getAllTypeOfLodging() {
    return this.http.get('api/type-lodging');
  }

  getCities() {
    return this.http.get('api/city/getCities');
  }

  getReservations() {
    return this.http.get('api/reservations');
  }

  getAllPriceList() {
    return this.http.get('api/priceList/getPriceLists');
  }

  getPriceListByLodgingId(lodgId: number) {
    return this.http.get('api/priceList/' + lodgId + '/' );
}
  getAllCategoryOfLodging() {
    return this.http.get('api/category-lodging');
  }

  getAllComment() {
    return this.http.get('api/comment/all-yes');
  }

  getAllUsers() {
    return this.http.get('api/users');
  }
}
