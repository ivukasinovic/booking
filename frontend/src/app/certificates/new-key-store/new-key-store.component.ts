import {Component, OnInit} from '@angular/core';
import {CertificatesService} from '../certificates.service';
import {Router} from '@angular/router';
import {KeyStore, NewKeyStore} from '../model';

@Component({
  selector: 'app-new-key-store',
  templateUrl: './new-key-store.component.html',
  styleUrls: ['./new-key-store.component.css']
})
export class NewKeyStoreComponent implements OnInit {

  error = '';
  keyStores: string[];
  keyStore: KeyStore;
  newK: NewKeyStore;

  constructor(private  cerService: CertificatesService, private router: Router) {
    this.keyStore = new KeyStore();
    this.newK = new NewKeyStore();
  }

  ngOnInit() {
  }

  createKeyStore( ime: string, sifra1: string, sifra2: string ) {

    console.log(' ____ ' + sifra1 + '    ' + sifra2);

    this.newK.name = ime;

  if (sifra1 === sifra2) {
    this.newK.password = sifra1;
     this.cerService.createKeyStore(this.newK).subscribe( // (result: KeyStore) => {
    //     this.keyStore = result;
    //     this.router.navigate(['/certificates/keyStore']);
    //     localStorage.setItem('keyStoreName', this.keyStore.name);
    //     localStorage.setItem('keyStorePw', this.keyStore.password);
    //   },
    //   error => {
    //     this.error = 'Pogresno korisnicko ime ili lozinka';
    //   }
    );
  } else {
    this.error = 'You must enter indentic passwords';

  }

  }

}
