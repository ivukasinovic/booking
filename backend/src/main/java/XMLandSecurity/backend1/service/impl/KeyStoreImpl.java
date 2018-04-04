package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.dto.KeyStoreDTO;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Ivan V. on 02-Apr-18
 */
@Service
public class KeyStoreImpl implements KeyStoreService {
    private KeyStore keyStore;

    public KeyStoreImpl() {
        try {
            keyStore = KeyStore.getInstance("JKS", "SUN");
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<String> getKeyStores() {
        File folder = new File("keystores");
        File[] listOfFiles = folder.listFiles();
        ArrayList<String> keyStores = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                keyStores.add(listOfFiles[i].getName());
            }
        }
        return keyStores;
    }

    @Override
    public KeyStoreDTO getKeyStore(String name, String password) {
        boolean succes =false;
        ArrayList<String> aliases = null;
        succes = loadKeyStore(name,password.toCharArray());
        if(!succes){
            return null;
        }
        try {
            Enumeration<String> aliasesEn = keyStore.aliases();
            aliases = Collections.list(aliasesEn);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return new KeyStoreDTO(name,password,aliases,null);
    }

    @Override
    public boolean createKeyStore(String name, String password) {
        loadKeyStore(null, password.toCharArray());
        return saveKeyStore(name, password.toCharArray());
    }

    @Override
    public void delete(String name) {
        File file = new File("keystores/" + name);
        boolean uspeo = file.delete();
    }

    @Override
    public void writeCertificate(String keyStoreName, String keyStorePw, Certificate certificate, String alias, PrivateKey pk) {
        loadKeyStore(keyStoreName, keyStorePw.toCharArray());
        try {
            keyStore.setCertificateEntry(alias, certificate);
            keyStore.setKeyEntry(alias, pk, keyStorePw.toCharArray(), new Certificate[]{certificate});
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        saveKeyStore(keyStoreName, keyStorePw.toCharArray());
    }

    @Override
    public IssuerData readIssuerFromStore(String keyStoreName, String keyStorePw, String alias) {
        loadKeyStore(keyStoreName, keyStorePw.toCharArray());
        try {
            Certificate cert = keyStore.getCertificate(alias);
            PrivateKey privKey = (PrivateKey) keyStore.getKey(alias, keyStorePw.toCharArray());
            X500Name issuerName = new JcaX509CertificateHolder((X509Certificate) cert).getSubject();
            return new IssuerData(privKey, issuerName);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean loadKeyStore(String fileName, char[] password) {
        try {
            if (fileName != null) {
                keyStore.load(new FileInputStream("keystores/" + fileName), password);
            } else {
                //Ako je cilj kreirati novi KeyStore poziva se i dalje load, pri cemu je prvi parametar null
                keyStore.load(null, password);
            }
            return true;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveKeyStore(String fileName, char[] password) {
        try {
            keyStore.store(new FileOutputStream("keystores/" + fileName), password);
            return true;
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

