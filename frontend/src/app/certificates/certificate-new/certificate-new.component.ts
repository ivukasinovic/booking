import {Component, OnInit} from '@angular/core';
import {CertificatesService} from '../certificates.service';
import {Router} from '@angular/router';
import {Certificate} from '../model';

@Component({
  selector: 'app-certificat',
  templateUrl: './certificate-new.component.html',
  styleUrls: ['./certificate-new.component.css']
})
export class CertificatComponent implements OnInit {

  niz = [];
  samoCekirani = [];        // Samo one kod kojih je cekiran cek-Box
  saveUsername: boolean;
  issuers: string[];
  issuer: string;
  private certificate: Certificate;

  constructor(private cerService: CertificatesService, private router: Router) {
    this.certificate = new Certificate();
    this.certificate.issuerName = '';
    this.saveUsername = false;
    this.issuer = '';

  }

  ngOnInit() {
    this.getIssuers();

  }

  getIssuers() {
    this.cerService.getIssuers()
      .subscribe((result: string[]) => {
        this.issuers = result;
      });
  }

  getCertificate(CN: string, SN: string, GN: string, ON: string, LN: string, Country: string, Email: string, CA: number) {
    if (CN !== '' && SN !== '' && GN !== '' && ON !== '' && LN !== '' && Country !== '' && Email !== '') {

      this.niz.push({
        CN: CN, SN: SN, GN: GN, ON: ON, LN: LN, C: Country, E: Email, CA: this.saveUsername
      });

      // if (SELEC != null) {
      //  this.certificate.issuerName = SELEC;
      // }

      this.certificate.issuerName = this.issuer;
      this.certificate.commonName = CN;
      this.certificate.orgNameUnit = CN;  // samo posle promeniti polje

      this.certificate.surname = SN;
      this.certificate.givenName = GN;
      this.certificate.orgName = ON;
      this.certificate.uid = LN;
      this.certificate.country = Country;
      this.certificate.email = Email;

      if (this.saveUsername === true) {
        this.certificate.caa = 1;
      } else {
        this.certificate.caa = 0;
      }

      console.log(this.certificate.caa + '  _______ ' + this.saveUsername);

      if (this.saveUsername === true) {
        // this.certificate.issuerName = SELEC;
        this.samoCekirani.push({CN: CN, SN: SN, GN: GN, ON: ON, LN: LN, C: Country, E: Email, CA: CA});
      }

      console.log(this.certificate);

      this.cerService.postCertificate(this.certificate)
        .subscribe((data: Certificate) => {
            this.certificate = data;
            alert('Uspesno kreiran sertifikat.');
            this.router.navigate(['certificates']);
          },
          error1 => {
            alert('Doslo je do greske, pokusajte ponovo!');
          });

      this.cerService.getCertificates();
    }

  } // kraj metode


}
