import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Lodging} from '../model';

@Injectable()
export class SearchService {

  constructor(private router: Router, private http: HttpClient) { }

  allLodgings() {
    return this.http.get('api/lodging/getLodgings');
  }
  searchLodging(cityName: string, nbrOfPersons: number) {
    return this.http.get('api/lodging/search/' + cityName + '/' + nbrOfPersons +'/');
  }

  getCities() {
    return this.http.get('api/city/getCities');
  }
}
