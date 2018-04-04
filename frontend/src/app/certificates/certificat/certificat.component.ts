import {Component, Input, OnInit} from '@angular/core';
import {CertificatesService} from '../certificates.service';
import {Router} from '@angular/router';
import {Certificate} from '../model';

@Component({
  selector: 'app-certificat',
  templateUrl: './certificat.component.html',
  styleUrls: ['./certificat.component.css']
})
export class CertificatComponent implements OnInit {

  niz = [];
  samoCekirani = [];        // Samo one kod kojih je cekiran cek-Box
  saveUsername: boolean;
  private certificate: Certificate;

  constructor(private cerService: CertificatesService, private router: Router) {
    this.certificate = new Certificate();

    this.certificate.issuerName = '';

  }

  ngOnInit() {

    this.saveUsername = false;
  }


  getCertificate(SELEC: string , CN: string, SN: string, GN: string, ON: string, LN: string, Country: string, Email: string, CA: boolean) {
    if (CN !== '' && SN !== '' && GN !== '' && ON !== '' && LN !== '' && Country !== '' && Email !== '') {

      this.niz.push({
        SELEC: SELEC, CN: CN, SN: SN, GN: GN, ON: ON, LN: LN, C: Country, E: Email, CA: this.saveUsername
      });

      if(SELEC != null) {
        this.certificate.issuerName = SELEC;
      } else {
        this.certificate.issuerName = '';

      }
      this.certificate.commonName = CN;
      this.certificate.orgNameUnit = CN;  // samo posle promeniti polje

      this.certificate.surname = SN;
      this.certificate.givenName = GN;
      this.certificate.orgName = ON;
      this.certificate.uid = LN;
      this.certificate.country = Country;
      this.certificate.email = Email;
      this.certificate.isCa = this.saveUsername;

      this.cerService.getCertificat(this.certificate).subscribe((result: Certificate) => {
        this.certificate = result; });

      console.log(CA + '  _______ ' + this.saveUsername);

      if (this.saveUsername === true) {
        this.samoCekirani.push({CN: CN, SN: SN, GN: GN, ON: ON, LN: LN, C: Country, E: Email, CA: CA});
      }

      console.log(this.certificate);

      this.cerService.getCertificates();
      window.location.reload();
      this.router.navigate(['certificates/keyStore']);
    }

  } // kraj metode


}
