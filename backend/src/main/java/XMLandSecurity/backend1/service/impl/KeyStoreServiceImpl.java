package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * Created by Ivan V. on 02-Apr-18
 */
@Service
public class KeyStoreServiceImpl implements KeyStoreService {
    private static final String PATH_CA = "ksCa.jks";
    private static final String PASSWORD_CA = "passwordCa";
    private static final String PATH_NONCA = "ksNonCa.jks";
    private static final String PASSWORD_NONCA = "passwordNonCa";
    private KeyStore keyStore;
    private String keyStoreName = "";
    private String keyStorePassword = "";

    public KeyStoreServiceImpl() {
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException | NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<X509Certificate> getCertificates() {
        List<X509Certificate> certificates = new ArrayList<>();

        try {
            for (int i = 0; i < 2; i++) {
                loadKeyStore(i);
                Enumeration<String> aliases = keyStore.aliases();

                while (aliases.hasMoreElements()) {
                    String alias = aliases.nextElement();

                    if (keyStore.isKeyEntry(alias)) {
                        certificates.add(getCertificate(alias, i).get());
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return certificates;
    }

    public List<CertificateDTO> getCertificatesDTO() {
        List<X509Certificate> certificates = getCertificates();
        List<CertificateDTO> certificateDTOS = new ArrayList<>();
        for (Certificate cert : certificates) {
            certificateDTOS.add(new CertificateDTO(cert));
        }
        return certificateDTOS;
    }

    @Override
    public Optional<X509Certificate> getCertificate(String alias, int i) {
        try {
            loadKeyStore(i);
            X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
            return Optional.ofNullable(cert);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public X509Certificate getCertificate(String alias) {
        for (int i = 0; i < 2; i++) {
            loadKeyStore(i);
            try {
                X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
                if (cert != null) {
                    return cert;
                }
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> getCertficatesSN() {
        List<X509Certificate> certificates = getCertificates();
        ArrayList<String> serialNumbers = new ArrayList<>();
        for (X509Certificate cert : certificates) {
            serialNumbers.add(cert.getSerialNumber().toString());
        }
        return serialNumbers;
    }

    @Override
    public CertificateDTO getCertificateDTO(String alias) {
        for (int i = 0; i < 2; i++) {
            loadKeyStore(i);
            try {
                X509Certificate cert = (X509Certificate) keyStore.getCertificate(alias);
                if (cert != null) {
                    return new CertificateDTO(cert);
                }
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public void writeCertificate(boolean isCa, Certificate certificate, String alias, PrivateKey pk) {
        if (isCa) {
            this.keyStoreName = PATH_CA;
            this.keyStorePassword = PASSWORD_CA;
            loadKeyStore(0);
        } else {
            loadKeyStore(1);
            this.keyStoreName = PATH_NONCA;
            this.keyStorePassword = PASSWORD_NONCA;
        }
        try {
            keyStore.setCertificateEntry(alias, certificate);
            keyStore.setKeyEntry(alias, pk, keyStorePassword.toCharArray(), new Certificate[]{certificate});
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        saveKeyStore(keyStoreName, keyStorePassword.toCharArray());
    }

    @Override
    public IssuerData readIssuerFromStore(String alias) {
        loadKeyStore(0);
        try {
            Certificate cert = keyStore.getCertificate(alias);
            PrivateKey privKey = (PrivateKey) keyStore.getKey(alias, PASSWORD_CA.toCharArray());
            X500Name issuerName = new JcaX509CertificateHolder((X509Certificate) cert).getSubject();
            return new IssuerData(privKey, issuerName);
        } catch (KeyStoreException | UnrecoverableKeyException | CertificateEncodingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<String> getIssuers() {
        loadKeyStore(0);
        ArrayList<String> issuers = null;
        try {
            Enumeration<String> aliases = keyStore.aliases();
            issuers = Collections.list(aliases);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return issuers;
    }

    @Override
    public boolean saveKeyStore(String fileName, char[] password) {
        try {
            keyStore.store(new FileOutputStream("keystores/" + fileName), password);
            return true;
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void createKeyStores() {
        try {
            keyStore.load(null, PASSWORD_CA.toCharArray());
            saveKeyStore(PATH_CA, PASSWORD_CA.toCharArray());
            keyStore.load(null, PASSWORD_NONCA.toCharArray());
            saveKeyStore(PATH_NONCA, PASSWORD_NONCA.toCharArray());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String alias) {
        for (int i = 0; i < 2; i++) {
            loadKeyStore(i);
            try {
                ArrayList<String> aliases = Collections.list(keyStore.aliases());
                if (aliases.contains(alias)) {
                    keyStore.deleteEntry(alias);
                    if (i == 0) {
                        saveKeyStore(PATH_CA, PASSWORD_CA.toCharArray());
                    } else if (i == 1) {
                        saveKeyStore(PATH_NONCA, PASSWORD_NONCA.toCharArray());
                    }
                    return;
                }
            } catch (KeyStoreException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteList(List<X509Certificate> certificates) {
        for (X509Certificate cert : certificates) {
            delete(cert.getSerialNumber().toString());
        }
    }


    @Override
    public void loadKeyStore(String keyStoreFile, String keyStorePassword) {
        keyStoreFile = "keystores/" + keyStoreFile;
        try {
            keyStore.load(new FileInputStream(keyStoreFile), keyStorePassword.toCharArray());
        } catch (IOException | NoSuchAlgorithmException | CertificateException e) {
            e.printStackTrace();
        }
    }

    // i = 0 pristupa se CA keystoru
    // i = 1 pristupa se NonCA keystoru
    // i = 2 pristupa se Root keystoru
    public void loadKeyStore(int i) {
        if (i == 0) {
            loadKeyStore(PATH_CA, PASSWORD_CA);
        } else if (i == 1) {
            loadKeyStore(PATH_NONCA, PASSWORD_NONCA);
        }
    }

}

