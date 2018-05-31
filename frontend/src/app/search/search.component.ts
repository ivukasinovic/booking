import { Component, OnInit } from '@angular/core';
import { Lodging, City } from '../model';
import {SearchService} from '../services/search.service';
import {Router} from '@angular/router';
@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  lod: Lodging[];
  searchCity: string ;
  city: City;
  cities: City[];
  cityName: string;
  constructor(private router: Router, private searchService: SearchService) {
    this.searchService.getCities().subscribe(
      (response: City[]) => {
        this.cities = response;
      });   // err kad stavim ispise
    this.searchService.allLodgings().subscribe(
      (response: Lodging[]) => {
        this.lod = response;
      });   // err kad stavim ispise

  }

  ngOnInit() {
  }

  searchByCity() {
    this.searchService.searchByCity(this.searchCity)
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
   // return this.cities[1].name;
  }



}
