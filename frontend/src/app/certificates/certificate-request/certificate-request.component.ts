import { Component, OnInit } from '@angular/core';
import {Certificate} from '../model';
import {CertificatesService} from "../certificates.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-certificate-request',
  templateUrl: './certificate-request.component.html',
  styleUrls: ['./certificate-request.component.css']
})
export class CertificateRequestComponent implements OnInit {

  cerReq: Certificate = new Certificate();
  constructor( private cerService: CertificatesService, private router: Router) {

  }

  ngOnInit() {
  }

  create() {
    this.cerService.postCertificateReq(this.cerReq)
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
