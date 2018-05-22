import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-additional-lodging',
  templateUrl: './additional-lodging.component.html',
  styleUrls: ['./additional-lodging.component.css']
})
export class AdditionalLodgingComponent implements OnInit {

  niz = [];

  constructor() { }

  ngOnInit() {
    this.niz.push('Parking');
    this.niz.push('WiFi');
    this.niz.push('Breakfast');
    this.niz.push('semi-pansion');
    this.niz.push('full-pansion');
    this.niz.push('TV');
    this.niz.push('MINI kitchn');
    this.niz.push('private bathroom');
  }

  add(noviAdditional){
    this.niz.push(noviAdditional);
  }

  brisi(br){
    this.niz.splice(br,1)
  }

}
