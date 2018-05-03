import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {CertificatesService} from '../certificates.service';
import {Certificate} from '../model';

@Component({
  selector: 'app-certificate-details',
  templateUrl: './certificate-details.component.html',
  styleUrls: ['./certificate-details.component.css']
})
export class CertificateDetailsComponent implements OnInit {
  serialNumber: number;
  certificate: Certificate;

  constructor(private routeA: ActivatedRoute, private certificateService: CertificatesService) {
  }

  ngOnInit() {
    this.routeA.params.subscribe(params => {
      this.serialNumber = params['id'];
    });
    this.getCertificateDetails(this.serialNumber);
  }

  getCertificateDetails(serialNumber: number) {
    this.certificateService.getCertificate(this.serialNumber)
      .subscribe((result: Certificate) => {
        this.certificate = result;
      },
        error1 => {
        alert('Doslo je do greske');
        });
  }

  back() {
    window.history.back();
  }
}
