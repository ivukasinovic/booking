import { Component, OnInit } from '@angular/core';
import {Lodging, Reservation} from '../model';
import {UserService} from '../services/user.service';
import {ReserveService} from '../services/reserve.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  tab: number;
  constructor() {
    this.tab = 0;
  }

  ngOnInit() {
  }

  setTab(t: number) {
    this.tab = t;
  }

  isSet(t: number) {
    return this.tab === t;
  }
}
