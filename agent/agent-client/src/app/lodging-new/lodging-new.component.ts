import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {AditionalServices, City, Lodging} from '../model';
import {LodgingService} from '../lodging.service';
import {forEach} from '@angular/router/src/utils/collection';

@Component({
  selector: 'app-lodging-new',
  templateUrl: './lodging-new.component.html',
  styleUrls: ['./lodging-new.component.css']
})
export class LodgingNewComponent implements OnInit {
  lodging: Lodging;
  cities: City[];
  categories: any[];
  types: any[];
  aditionServices:  AditionalServices[];
  imagesList: String[];
  newImages: String[];

  nizCekiranih: number[];
  cekiraniSlanje: number[];

  constructor(private lodgingService: LodgingService) {
    this.lodging = new Lodging();
    this.imagesList = [];
    this.newImages = [];
    this.nizCekiranih = [];
    this.cekiraniSlanje = [];
    this.imagesList.push('');
  }

  ngOnInit() {
    this.getCities();
    this.getCategories();
    this.getTypes();
    this.getAditionalServices();
  }

  createLodging() {
    this.lodging.additionService = this.nizCekiranih;

    for (const img of this.imagesList) {
      if (img.length > 0) {
        this.newImages.push(img);
      }
    }
    this.lodging.imagesList = this.newImages;
    this.lodgingService.createLodging(this.lodging)
      .subscribe(() => {
        alert('Success');
        location.reload();
      },
        error1 => {
        alert('Error, try again!');
        location.reload();
        });
  }
  getCities() {
    this.lodgingService.getCities()
      .subscribe((response: City[]) => {
        this.cities = response;
      });
  }
  getCategories() {
    this.lodgingService.getCategories()
      .subscribe((response: any[]) => {
        this.categories = response;
      });
  }
  getTypes() {
    this.lodgingService.getTypes()
      .subscribe((response: any[]) => {
        this.types = response;
      });
  }

  getAditionalServices() {
    this.lodgingService.getAdditionalServices()
      .subscribe((response: AditionalServices[]) => {
        this.aditionServices = response;
      });
  }

  vrati(id: number) {
    const text = document.getElementById('text');
    console.log('check');
    const element: HTMLInputElement =  <HTMLInputElement>document.getElementById(id.toString());
    const isChecked = element.checked;
    if (element.checked === true) {
      //  text.style.display = 'block';

      this.nizCekiranih.push(id);

    } else {
      for (let i = 0 ; i < this.nizCekiranih.length ; i++ ) {
        if (this.nizCekiranih[i] === id ) {
          console.log('brisem iz liste ' + id + ' na poziciji ' + i);
          this.nizCekiranih.splice(i, 1);
          console.log('duzina liste ' + this.nizCekiranih.length);
        }
      }
    }

  }
  addMore() {
    this.imagesList.push('');
  }




}
