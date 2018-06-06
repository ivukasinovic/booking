import { Component, OnInit } from '@angular/core';
import {Lodging} from '../model';

@Component({
  selector: 'app-lodging-new',
  templateUrl: './lodging-new.component.html',
  styleUrls: ['./lodging-new.component.css']
})
export class LodgingNewComponent implements OnInit {
  lodging: Lodging;
  constructor() {
    this.lodging = new Lodging();
  }

  ngOnInit() {
  }

  createLodging() {

  }

}
