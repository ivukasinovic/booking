import {Component, OnInit} from '@angular/core';
import {CertificatesService} from '../certificates.service';

@Component({
  selector: 'app-select-key-store',
  templateUrl: './select-key-store.component.html',
  styleUrls: ['./select-key-store.component.css']
})
export class SelectKeyStoreComponent implements OnInit {
  keyStores: string[];
  selected: string;
  password: string;
  constructor(private  cerService: CertificatesService) {
  }

  ngOnInit() {
    this.cerService.getKeyStores()
      .subscribe(keyStores => {
        this.keyStores = keyStores;
      });
    console.log(this.password);
  }
  getKeyStore() {
    console.log(this.selected + this.password);
    this.cerService.getKeyStore(this.selected, this.password)
  }


}
