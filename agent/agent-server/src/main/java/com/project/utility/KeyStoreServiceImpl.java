package com.project.utility;

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


@Service
public class KeyStoreServiceImpl {
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


    public ArrayList<String> getCertficatesSN() {
        List<X509Certificate> certificates = getCertificates();
        ArrayList<String> serialNumbers = new ArrayList<>();
        for (X509Certificate cert : certificates) {
            serialNumbers.add(cert.getSerialNumber().toString());
        }
        return serialNumbers;
    }

    public PrivateKey readPrivateKey(String alias) {
        loadKeyStore(1);
        try {
            PrivateKey privKey = (PrivateKey) keyStore.getKey(alias, PASSWORD_NONCA.toCharArray());
            return  privKey;
        } catch (KeyStoreException | UnrecoverableKeyException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Certificate readCertificate(String alias) {
        loadKeyStore(1);
        try {
            Certificate cert = keyStore.getCertificate(alias);
            return  cert;
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return null;
    }




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

