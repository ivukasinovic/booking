import { TestBed, inject } from '@angular/core/testing';

import { CertificatesServiceService } from './certificates-service.service';

describe('CertificatesServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CertificatesServiceService]
    });
  });

  it('should be created', inject([CertificatesServiceService], (service: CertificatesServiceService) => {
    expect(service).toBeTruthy();
  }));
});
