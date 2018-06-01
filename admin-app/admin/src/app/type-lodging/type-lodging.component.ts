import {Component, Input, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {AuthService} from '../auth.service';
import {Comment, Tip, User} from '../models/user';

@Component({
  selector: 'app-type-lodging',
  templateUrl: './type-lodging.component.html',
  styleUrls: ['./type-lodging.component.css']
})
export class TypeLodgingComponent implements OnInit {

  pritisni = false;
  checked = false;
  // indeterminate = false;
  // labelPosition = 'after';
  // disabled = false;
  niz = [];

  tip: Tip[];

  noviTip: Tip;

  constructor(private authService: AuthService, private router: Router) {
    this.noviTip = new Tip();
  }

  ngOnInit() {
    // this.niz.push('Hotel!');
    // this.niz.push('Bed and breakfast !');
    // this.niz.push('Apartman!');
    this.authService.getTypeOfLodging()
      .subscribe(
        (response: Tip[]) => {
          this.tip = response;
        });   // err kad stavim ispise mi gresku

  }

  brisi(c: number) {
    // this.niz.splice(c,1);
    this.authService.deleteType(c)
      .subscribe(response => {
          alert('Uspesno ste obrisali !!!');
          window.location.reload();
        },
        err => {
          alert('Niste uspeli obrisati(Doslo je do greske)');
        });


  }

  add(ime: string) {
    this.pritisni = true;
    //  this.niz.push(noviTip);
    this.noviTip.label = ime;
    //  this.noviTip.label= ime;


    this.authService.addTypeOfLodging(this.noviTip)
      .subscribe((data: Tip) => {
          alert('Succes registration ' + this.noviTip.label + '!');
          this.router.navigate(['/admin']);
          window.location.reload();
        },
        error1 => {
          alert('Error!');
        }
      );
  }

}
