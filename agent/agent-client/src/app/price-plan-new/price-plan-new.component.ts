import { Component, OnInit } from '@angular/core';
import {LodgingService} from '../lodging.service';
import {Lodging, PricePlan} from '../model';
import {error} from 'selenium-webdriver';

@Component({
  selector: 'app-price-plan-new',
  templateUrl: './price-plan-new.component.html',
  styleUrls: ['./price-plan-new.component.css']
})
export class PricePlanNewComponent implements OnInit {
  lodgings: Lodging[];
  pricePlan: PricePlan;
  constructor(private lodgingService: LodgingService) {
    this.lodgings = [];
    this.pricePlan = new PricePlan();
  }

  ngOnInit() {
    this.lodgingService.getLodgings()
      .subscribe((response: Lodging[]) => {
        this.lodgings = response;
      }, error1 => {
        alert('Error with fetching lodgings!');
      });
  }

  setPricePlan() {
    this.lodgingService.postPricePlan(this.pricePlan)
      .subscribe(response => {
        alert(response);
      });
  }

}
