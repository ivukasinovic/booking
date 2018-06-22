import { Component, OnInit } from '@angular/core';
import {UserDto} from '../model';
import {UserService} from '../services/user.service';

@Component({
  selector: 'app-user-info',
  templateUrl: './user-info.component.html',
  styleUrls: ['./user-info.component.css']
})
export class UserInfoComponent implements OnInit {

  userDto: UserDto = new UserDto();

  constructor(private userService: UserService) {
    this.userService.getUserInfo().subscribe((res: UserDto) => {
      this.userDto = res;
    });
  }

  ngOnInit() {
  }

}
