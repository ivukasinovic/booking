import {Component, OnInit} from '@angular/core';
import {Additional, Tip} from '../models/user';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';

@Component({
  selector: 'app-additional-lodging',
  templateUrl: './additional-lodging.component.html',
  styleUrls: ['./additional-lodging.component.css']
})
export class AdditionalLodgingComponent implements OnInit {

  niz = [];

  tip: Additional[];

  noviTip: Additional;

  constructor(private authService: AuthService, private router: Router) {
    this.noviTip = new Additional();

    this.authService.getAdditional()
      .subscribe(
        (response: Additional[]) => {
          this.tip = response;
        });   // err k
  }

  ngOnInit() {
    // this.niz.push('Parking');
    // this.niz.push('WiFi');
    // this.niz.push('Breakfast');
    // this.niz.push('semi-pansion');
    // this.niz.push('full-pansion');
    // this.niz.push('TV');
    // this.niz.push('MINI kitchn');
    // this.niz.push('private bathroom');


  }

  add(noviAdditional: string) {
    //  this.niz.push(noviAdditional);

    this.noviTip.name = noviAdditional;

    this.authService.addAdditional(this.noviTip)
      .subscribe((data: Additional) => {
          // alert('Succes ' + data.name + '!');
          alert('Succes ' + this.noviTip.id + '!');
          //  this.router.navigate(['/admin']);
          window.location.reload();
        },
        error1 => {
       //   alert('Error!');
          window.location.reload();
        }
      );
  }

  brisi(br: number) {
    // this.niz.splice(br,1)
    this.authService.deleteAdditional(br)
      .subscribe(response => {
          alert('Uspesno ste obrisali !!!');
          window.location.reload();
        },
        err => {
          alert('Niste uspeli obrisati(Doslo je do greske)');
        });
  }

}
