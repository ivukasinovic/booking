import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-type-lodging',
  templateUrl: './type-lodging.component.html',
  styleUrls: ['./type-lodging.component.css']
})
export class TypeLodgingComponent implements OnInit {

  pritisni = false;
  checked = false;
  // indeterminate = false;
  // labelPosition = 'after';
  // disabled = false;
  niz = [];

  constructor() { }

  ngOnInit() {
    this.niz.push('Hotel!');
    this.niz.push('Bed and breakfast !');
    this.niz.push('Apartman!');

  }

  brisi(c){
    this.niz.splice(c,1);
  }

  add(noviTip){
    this.pritisni = true;
    this.niz.push(noviTip);
  }

}
