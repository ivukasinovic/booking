import { TestBed, inject } from '@angular/core/testing';

import { CertificatesService } from './certificates.service';

describe('CertificatesService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CertificatesService]
    });
  });

  it('should be created', inject([CertificatesService], (service: CertificatesService) => {
    expect(service).toBeTruthy();
  }));
});
