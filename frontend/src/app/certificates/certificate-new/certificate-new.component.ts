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
  issuers: string[];
  certificate: Certificate;
  isCa: boolean;

  constructor(private cerService: CertificatesService, private router: Router) {
    this.certificate = new Certificate();
    this.isCa = false;

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

  create() {
    console.log(this.certificate);
    if (this.isCa) {
      this.certificate.caa = 1;
    } else {
      if ((this.certificate.issuerSerialNumber == null) || (this.certificate.issuerSerialNumber === '')) {
        alert('Must choose issuer or make CA checked!');
        return;
      }
      this.certificate.caa = 0;
    }
    this.cerService.postCertificate(this.certificate)
      .subscribe(data => {
          alert('Success!');
          this.router.navigate(['/certificates']);
          location.reload();
        },
        error1 => {
          alert('Error!');
        });
  }


}
