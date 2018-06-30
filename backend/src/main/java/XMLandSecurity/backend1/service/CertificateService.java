package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.SubjectData;
import XMLandSecurity.backend1.model.dto.CertificateDTO;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.List;

public interface CertificateService {

    List<CertificateDTO> convertToDTO(List<X509Certificate> certificates);

    X509Certificate generateCertificate(CertificateDTO certificateDTO);

    PKCS10CertificationRequest generateCertificateRequest(CertificateDTO certificateDTO);

    List<CertificateDTO> getAllCSRs();

    void aproveCSR(String id);

    void deleteCSR(String id);

    SubjectData newSubjectData(CertificateDTO certificate, PublicKey publicKey);

    IssuerData newIssuerData(CertificateDTO certificate);

    String check(String id);

    String download(String id);

    void revoke(String serialNumber);

    List<X509Certificate> readRevoked();


}
