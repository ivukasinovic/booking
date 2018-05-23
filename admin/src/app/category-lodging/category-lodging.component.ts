import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-category-lodging',
  templateUrl: './category-lodging.component.html',
  styleUrls: ['./category-lodging.component.css']
})
export class CategoryLodgingComponent implements OnInit {

  currentRate = 3;
  maximum = 5;

  checked = false;

  men(){
    this.checked = !this.checked;

  }

  constructor() { }

  ngOnInit() {
  }

}
