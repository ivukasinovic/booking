import {Component, OnInit} from '@angular/core';
import {CertificatesService} from '../certificates.service';
import {KeyStore} from '../model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-select-key-store',
  templateUrl: './select-key-store.component.html',
  styleUrls: ['./select-key-store.component.css']
})
export class SelectKeyStoreComponent implements OnInit {
  keyStores: string[];
  keyStore: KeyStore;
  error = '';

  constructor(private  cerService: CertificatesService, private router: Router) {
    this.keyStore = new KeyStore();
  }

  ngOnInit() {
    this.cerService.getKeyStores()
      .subscribe(keyStores => {
        this.keyStores = keyStores;
      });
  }
  getKeyStore() {
    this.cerService.getKeyStore(this.keyStore)
      .subscribe((result: KeyStore) => {
        this.keyStore = result;
        this.router.navigate(['/certificates/keyStore']);
        },
        error => {
          this.error = 'Pogresno korisnicko ime ili lozinka';
        });
  }

}
