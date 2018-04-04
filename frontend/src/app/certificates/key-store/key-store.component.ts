import {Component, OnInit} from '@angular/core';
import {CertificatesService} from '../certificates.service';
import {KeyStore} from '../model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-key-store',
  templateUrl: './key-store.component.html',
  styleUrls: ['./key-store.component.css']
})
export class KeyStoreComponent implements OnInit {
  keyStore: KeyStore;
  constructor(private cerService: CertificatesService, private router: Router) {
    this.keyStore = new KeyStore();
    this.keyStore.aliases = [];
  }

  ngOnInit() {
    this.getKeyStore();
  }
  getKeyStore() {
    this.keyStore.name = localStorage.getItem('keyStoreName');
    this.keyStore.password = localStorage.getItem('keyStorePw');
    this.cerService.getKeyStore(this.keyStore)
      .subscribe((result: KeyStore) => {
          this.keyStore = result; });
  }
  exitKeyStore() {
    localStorage.removeItem('keyStoreName');
    localStorage.removeItem('keyStorePw');
    this.router.navigate(['/selectKeyStore']);
  }
}
