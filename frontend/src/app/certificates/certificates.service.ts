import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {KeyStore} from './model';

@Injectable()
export class CertificatesService {

  constructor(private http: HttpClient) { }

  getKeyStores(): Observable<string[]> {
    return this.http.get<string[]>('/api/keyStore');
  }
  getKeyStore(name: string, password: string) {
    // const httpParams = new HttpParams();
    // httpParams.append('name', name);
    // httpParams.append('password', password);
    // const headers = new HttpHeaders();
    // headers.append('Content-Type', 'application/json');
    // const params = new URLSearchParams();
    // params.append('name', name);
    // params.append('password', password);
    //
    // return this.http.get('/api/keyStore/getKeyStore',{headers: headers, params: params});
  }
}
