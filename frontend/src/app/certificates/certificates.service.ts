import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {Certificate, KeyStore} from './model';

@Injectable()
export class CertificatesService {
  private ks: KeyStore;

  constructor(private http: HttpClient) {
    this.ks = new KeyStore();
  }

  getCertificates(): Observable<Certificate[]> {
    return this.http.get<Certificate[]>('/api/certificates');
  }

  postCertificate(certificate: Certificate) {
    return this.http.post('/api/certificates', certificate);
  }

  getCertificate(serialNumber: number) {
    const url = '/api/certificates/' + serialNumber;
    return this.http.get(url);
  }

  getIssuers() {
    return this.http.get('/api/certificates/issuers');
  }

}
