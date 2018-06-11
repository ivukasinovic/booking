import { Component, OnInit } from '@angular/core';
import {Message} from '../model';
import {ReserveService} from '../services/reserve.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-send-message',
  templateUrl: './send-message.component.html',
  styleUrls: ['./send-message.component.css']
})
export class SendMessageComponent implements OnInit {

  msg: Message;
  id: string;
  constructor(private reserveService: ReserveService, private router: Router) {
    this.msg = new Message();
  }

  ngOnInit() {
  }

  sendMessage2() {
    this.id = localStorage.getItem('res-id');
    this.reserveService.sendMsg(this.id, this.msg).subscribe();
    this.router.navigateByUrl('/profile');
  }

}
