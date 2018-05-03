import {Component, OnInit} from '@angular/core';
import {CertificatesService} from '../certificates.service';
import {Certificate} from '../model';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-certificate-list',
  templateUrl: './certificate-list.component.html',
  styleUrls: ['./certificate-list.component.css']
})
export class CertificateListComponent implements OnInit {
  certificates: Certificate[];
  displayedColumns = ['serialNumber', 'commonName', 'surname', 'orgName'];
  dataSource = null;

  constructor(private certificateService: CertificatesService) {
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
  }

  ngOnInit() {
    console.log('test');
    this.certificateService.getCertificates()
      .subscribe((data: Certificate[]) => {
          this.certificates = data;
          this.dataSource = new MatTableDataSource(data);
        },
        error1 => {
          alert('Nije uspelo preuzimanje sertifikata!');
        });
  }

}
