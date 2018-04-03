import { TestBed, inject } from '@angular/core/testing';

import { CertificatService } from './certificat.service';

describe('CertificatService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CertificatService]
    });
  });

  it('should be created', inject([CertificatService], (service: CertificatService) => {
    expect(service).toBeTruthy();
  }));
});
