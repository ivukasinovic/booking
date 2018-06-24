import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-category-lodging',
  templateUrl: './category-lodging.component.html',
  styleUrls: ['./category-lodging.component.css']
})
export class CategoryLodgingComponent implements OnInit {

  currentRate = 6;
  maximum = 6;

  checked = false;

  men() {
    this.checked = !this.checked;

  }

  constructor() {
  }

  ngOnInit() {
  }

}
