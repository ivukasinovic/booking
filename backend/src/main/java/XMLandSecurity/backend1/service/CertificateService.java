package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.SubjectData;
import XMLandSecurity.backend1.model.dto.CertificateDTO;
import org.springframework.web.bind.annotation.RequestHeader;

import java.security.KeyPair;
import java.security.cert.Certificate;

public interface CertificateService {

    Certificate generateCertificate(CertificateDTO certificateDTO, String keyStoreName, String keyStorePw);

    SubjectData newSubjectData(CertificateDTO certificate);

    IssuerData newIssuerData(CertificateDTO certificate);


}
