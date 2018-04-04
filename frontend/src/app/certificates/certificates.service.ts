import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Certificate, KeyStore} from './model';

@Injectable()
export class CertificatesService {
  private ks: KeyStore;
  private certificat: Certificate;

  constructor(private http: HttpClient) {
    this.ks = new KeyStore();
  }

  getKeyStores(): Observable<string[]> {
    return this.http.get<string[]>('/api/keyStore');
  }
  getKeyStore(keyStore: KeyStore) {
    return this.http.post('/api/keyStore/getKeyStore', keyStore);
  }

  getCertificates(): Observable<Certificate[]> {                  // treba napraviti kontroler za ovo
    return this.http.get<Certificate[]>('/api/certificates');
  }
  getCertificat(certificat: Certificate) {
    return this.http.post('/api/certificates/genCertificate', certificat);
  }

}
