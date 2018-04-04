import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {KeyStore} from './model';

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
}
