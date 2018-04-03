package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.model.IssuerData;

import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.ArrayList;

/**
 * Created by Ivan V. on 02-Apr-18
 */
public interface KeyStoreService {

    ArrayList<String> getKeyStores();

    boolean createKeyStore(String name, String password);

    void delete(String id);

    void writeCertificate(String keyStoreName, String keyStorePw, Certificate certificate, String alias, PrivateKey pk);

    IssuerData readIssuerFromStore(String keyStoreName, String keyStorePw, String alias);

}
