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
  searchLodging(cityName: string, nbrOfPersons: number, dateStart: Date, dateEnd: Date, typeOfLodging: string, as: AditionalServices) {
    return this.http.post('api/lodging/search/' + cityName + '/' + nbrOfPersons +
      '/' + dateStart + '/' + dateEnd + '/' + typeOfLodging + '/', as );
  }

  getAllAditionalServices() {
    return this.http.get('api/addtional-service');
  }

  getAllTypeOfLodging(){
    return this.http.get('api/type-lodging');
  }

  getCities() {
    return this.http.get('api/city/getCities');
  }

  getReservations() {
    return this.http.get('api/reservations');
  }


}
