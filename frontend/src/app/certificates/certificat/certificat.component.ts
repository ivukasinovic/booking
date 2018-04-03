import {Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-certificat',
  templateUrl: './certificat.component.html',
  styleUrls: ['./certificat.component.css']
})
export class CertificatComponent implements OnInit {

  niz = [];

  constructor() {
  }

  ngOnInit() {
  }

  // Od dugmeta kada  potvrdi da pravi sertifikat ...
  addNiz(CN: string, SN: string, GN: string, ON: string, LN: string, Country: string, Email: string, CA: boolean) {
    if (CN !== '' && SN !== '' && GN !== '' && ON !== '' && LN !== '' && Country !== '' && Email !== '') {
      this.niz.push({
        CN: CN, SN: SN, GN: GN, ON: ON, LN: LN, C: Country, E: Email, CA: CA
      });
    }

  } // kraj metode

}
