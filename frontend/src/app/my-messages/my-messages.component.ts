import { Component, OnInit } from '@angular/core';
import {Message} from "../model";
import {UserService} from "../services/user.service";

@Component({
  selector: 'app-my-messages',
  templateUrl: './my-messages.component.html',
  styleUrls: ['./my-messages.component.css']
})
export class MyMessagesComponent implements OnInit {

  received: Message[];
  sent: Message[];
  tab: number;

  constructor(private userService: UserService) {
    this.tab = -1;
  }

  ngOnInit() {
    this.userService.getReceivedMessages().subscribe((response: Message[]) => {
      this.received = response;
    });
    this.userService.getSentMessages().subscribe((response: Message[]) => {
      this.sent = response;
    });
  }


  setTab(t: number) {
    this.tab = t;
  }

  isSet(t: number) {
    return this.tab === t;
  }

}
