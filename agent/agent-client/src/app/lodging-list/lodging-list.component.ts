import { Component, OnInit } from '@angular/core';
import {Lodging} from '../model';
import {LodgingService} from '../lodging.service';

@Component({
  selector: 'app-lodging-list',
  templateUrl: './lodging-list.component.html',
  styleUrls: ['./lodging-list.component.css']
})
export class LodgingListComponent implements OnInit {
lodgings: Lodging[];
  constructor(private lodgingService: LodgingService) { }

  ngOnInit() {
    this.lodgingService.getLodgings()
      .subscribe((response: Lodging[]) => {
        this.lodgings = response;
      });
  }

}
