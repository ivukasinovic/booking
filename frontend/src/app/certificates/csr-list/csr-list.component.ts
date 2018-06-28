import { Component, OnInit } from '@angular/core';
import {Certificate} from '../model';
import {CertificatesService} from '../certificates.service';

@Component({
  selector: 'app-csr-list',
  templateUrl: './csr-list.component.html',
  styleUrls: ['./csr-list.component.css']
})
export class CsrListComponent implements OnInit {
  certificates: Certificate[];
  role: string;

  constructor(private certificateService: CertificatesService) {

  }

  ngOnInit() {

    this.certificateService.getCSRList()
      .subscribe((data: Certificate[]) => {
          this.certificates = data;
        },
        error1 => {
          alert('Nije uspelo preuzimanje csr liste!');
        });
    this.role = localStorage.getItem('role');
  }

  delete(id: string) {
    this.certificateService.deleteCSR(id)
      .subscribe(response => {
        alert('Success');
        location.reload();
      }, error1 => {
        alert('Nije uspelo brisanje csr zahtjeva!');
      });

  }

  aprove(id: string) {
    this.certificateService.aproveCSR(id)
      .subscribe(response => {
        alert('Success');
        location.reload();
      });
  }
}
