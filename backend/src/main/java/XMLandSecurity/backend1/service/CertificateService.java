package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.SubjectData;
import XMLandSecurity.backend1.model.dto.CertificateDTO;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.X509Certificate;
import java.util.List;

public interface CertificateService {

    List<CertificateDTO> convertToDTO(List<X509Certificate> certificates);

    X509Certificate generateCertificate(CertificateDTO certificateDTO);

    PKCS10CertificationRequest generateCertificateRequest(CertificateDTO certificateDTO);

    SubjectData newSubjectData(CertificateDTO certificate);

    IssuerData newIssuerData(CertificateDTO certificate);

    String check(String id);

    String download(String id);

    void revoke(String serialNumber);

    List<X509Certificate> readRevoked();


}
