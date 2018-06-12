import { Component, OnInit } from '@angular/core';
import {LodgingService} from '../lodging.service';
import {Message} from '../model';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {
  messages: Message[];
  constructor(private lodgingService: LodgingService) {
  }

  ngOnInit() {
    this.getMessages();
  }

  getMessages() {
    this.lodgingService.getMessages()
      .subscribe((response: Message[]) => {
        this.messages = response;
      });
  }
  reply(sender: string) {
    alert('Not working yet!');
  }

}
