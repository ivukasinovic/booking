import {Component, OnInit} from '@angular/core';
import {CertificatesService} from '../certificates.service';
import {Certificate} from '../model';
import {saveAs} from 'file-saver/FileSaver';

@Component({
  selector: 'app-certificate-list',
  templateUrl: './certificate-list.component.html',
  styleUrls: ['./certificate-list.component.css']
})
export class CertificateListComponent implements OnInit {
  certificates: Certificate[];
  file: string;
  respond: string;
  serial: number;
  role: string;

  constructor(private certificateService: CertificatesService) {
  }

  ngOnInit() {
    console.log('test');
    this.certificateService.getCertificates()
      .subscribe((data: Certificate[]) => {
          this.certificates = data;
        },
        error1 => {
          alert('Nije uspelo preuzimanje sertifikata!');
        });
    this.role = localStorage.getItem('role');
  }

  check(serialNumber: number) {
    this.certificateService.check(serialNumber)
      .subscribe((data: string) => {
          this.respond = data;
          alert('Certificate is ' + this.respond);
        },
        error1 => {
          alert('Error!');
        });
  }

  download(serialNumber: number) {
    this.certificateService.download(serialNumber)
      .subscribe((data: string) => {
        this.file = data;
        this.saveFile(serialNumber);
      });
  }

  saveFile(serialNumber: number) {
    console.log(this.file + 'BAOO');
    const blob = new Blob([this.file], {type: 'text/cer'});
    const filename = serialNumber + '.cer';
    saveAs(blob, filename);
  }

  delete(serialNumber: number) {
    this.certificateService.delete(serialNumber)
      .subscribe();
    location.reload();
  }

  revoke(serialNumber: number) {
    this.certificateService.revoke(serialNumber)
      .subscribe();
    location.reload();
  }


}
