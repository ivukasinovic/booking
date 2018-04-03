import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Wlcome to Security';

  show = false;
  show2 = false;

  enable1(row: any) {
    row.expand = true;
  }


}
