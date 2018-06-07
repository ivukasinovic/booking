import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Lodging, TypeOfLodging} from '../model';

@Injectable()
export class SearchService {

  constructor(private router: Router, private http: HttpClient) { }

  allLodgings() {
    return this.http.get('api/lodging/getLodgings');
  }
  searchLodging(cityName: string, nbrOfPersons: number, dateStart: Date, dateEnd: Date, typeOfLodging: string) {
    return this.http.get('api/lodging/search/' + cityName + '/' + nbrOfPersons +
      '/' + dateStart + '/' + dateEnd + '/' + typeOfLodging + '/');
  }

  getCities() {
    return this.http.get('api/city/getCities');
  }

  getReservations() {
    return this.http.get('api/reservations');
  }


}
