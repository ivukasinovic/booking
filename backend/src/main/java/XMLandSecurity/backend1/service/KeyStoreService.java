package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.dto.CertificateDTO;

import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Ivan V. on 02-Apr-18
 */
public interface KeyStoreService {

    List<X509Certificate> getCertificates();

    Optional<X509Certificate> getCertificate(String alias, int i);

    X509Certificate getCertificate(String alias);

    CertificateDTO getCertificateDTO(String alias);

    List<CertificateDTO> getCertificatesDTO();

    void writeCertificate(boolean isCa, Certificate certificate, String alias, PrivateKey pk);

    IssuerData readIssuerFromStore(String alias);

    ArrayList<String> getIssuers();

    void loadKeyStore(String keyStoreName, String keyStorePassword);

    boolean saveKeyStore(String fileName, char[] password);

    void createKeyStores();

    void delete(String alias);

}
