import { Component, OnInit } from '@angular/core';
import {City, Lodging} from '../model';
import {LodgingService} from '../lodging.service';

@Component({
  selector: 'app-lodging-new',
  templateUrl: './lodging-new.component.html',
  styleUrls: ['./lodging-new.component.css']
})
export class LodgingNewComponent implements OnInit {
  lodging: Lodging;
  cities: City[];
  categories: any[];
  types: any[];

  constructor(private lodgingService: LodgingService) {
    this.lodging = new Lodging();
  }

  ngOnInit() {
    this.getCities();
    this.getCategories();
    this.getTypes();
  }

  createLodging() {

  }
  getCities() {
    this.lodgingService.getCities()
      .subscribe((response: City[]) => {
        this.cities = response;
      });
  }
  getCategories() {
    this.lodgingService.getCategories()
      .subscribe((response: any[]) => {
        this.categories = response;
      });
  }
  getTypes(){
    this.lodgingService.getTypes()
      .subscribe((response: any[]) => {
        this.types = response;
      });
  }
}
