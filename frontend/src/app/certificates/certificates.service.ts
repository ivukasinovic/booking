import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Certificate, KeyStore, NewKeyStore} from './model';

@Injectable()
export class CertificatesService {
  private ks: KeyStore;

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
  getCertificateDetail(serialNumber: number) {
    const url = '/api/certificates/' + serialNumber;
    return this.http.get(url);
  }
  createKeyStore(newk: NewKeyStore) {
    const url = '/api/keyStore?name=' + newk.name;
    return this.http.post(url, newk , {
        observe: 'body',
        params: new HttpParams().set('password', newk.password)
        //  params: new HttpParams.set('password', newk.password)
      }
    );  // {name: newk.name , passsword: newk.password }
  }
  getIssuers() {
    return this.http.get('/api/certificates/getIssuers');
  }

}
