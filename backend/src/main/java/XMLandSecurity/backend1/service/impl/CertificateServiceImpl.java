package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.certificate.CertificateGenerator;
import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.SubjectData;
import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.CertificateService;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.CertIOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private KeyStoreService keyStoreService;

    private KeyPair keyPair;

    @Override
    public List<CertificateDTO> convertToDTO(List<X509Certificate> certificates) {
        ArrayList<CertificateDTO> certificateDTOS = new ArrayList<>();
        for (X509Certificate cert : certificates) {
            certificateDTOS.add(new CertificateDTO(cert));
        }
        return certificateDTOS;
    }

    @Override
    public X509Certificate generateCertificate(CertificateDTO certificateDTO) {
        // Serijski broj sertifikata
        int randomNum = 0 + (int) (Math.random() * 10000000);
        String sn = String.valueOf(randomNum);
        certificateDTO.setSerialNumber(sn);
        keyPair = generateKeyPair();
        SubjectData sd = newSubjectData(certificateDTO);
        IssuerData id = newIssuerData(certificateDTO);


        CertificateGenerator generator = new CertificateGenerator();
        X509Certificate certificate = null;
        try {
            certificate = generator.generateCertificate(sd, id, certificateDTO.getisCa(), certificateDTO.getIssuerSerialNumber());
        } catch (CertIOException e) {
            e.printStackTrace();
        }
        keyStoreService.writeCertificate(certificateDTO.getisCa(), certificate, certificate.getSerialNumber().toString(), sd.getPrivateKey());

        return certificate;
    }


    public SubjectData newSubjectData(CertificateDTO certificate) {

        //Datumi od kad do kad vazi sertifikat
        Date startDate = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, 1);
        Date endDate = c.getTime();


        //Serijski broj sertifikata
        String sn = certificate.getSerialNumber();
        //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, certificate.getCommonName());
        builder.addRDN(BCStyle.SURNAME, certificate.getSurname());
        builder.addRDN(BCStyle.GIVENNAME, certificate.getGivenName());
        builder.addRDN(BCStyle.O, certificate.getOrgName());
        builder.addRDN(BCStyle.OU, certificate.getOrgNameUnit());
        builder.addRDN(BCStyle.C, certificate.getCountry());
        builder.addRDN(BCStyle.E, certificate.getEmail());
        //UID (USER ID) je ID korisnika
        builder.addRDN(BCStyle.UID, sn);

        //podaci o sertifikatu  javni kljuc, podaci o vlasniku, serijski broj, od kad do kad vazi
        return new SubjectData(keyPair.getPublic(), keyPair.getPrivate(), builder.build(), sn, startDate, endDate);

    }

    public IssuerData newIssuerData(CertificateDTO certificate) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);

        String isn = certificate.getIssuerSerialNumber();
        if ((isn == "") || isn == null) { //ako uzmemo u obzir da ce biti prazan string ako zeli issuera da doda

            builder.addRDN(BCStyle.CN, certificate.getCommonName());
            builder.addRDN(BCStyle.SURNAME, certificate.getSurname());
            builder.addRDN(BCStyle.GIVENNAME, certificate.getGivenName());
            builder.addRDN(BCStyle.O, certificate.getOrgName());
            builder.addRDN(BCStyle.OU, certificate.getOrgNameUnit());
            builder.addRDN(BCStyle.C, certificate.getCountry());
            builder.addRDN(BCStyle.E, certificate.getEmail());
            builder.addRDN(BCStyle.UID, certificate.getSerialNumber());

            return new IssuerData(keyPair.getPrivate(), builder.build());
        } else {
            IssuerData id = keyStoreService.readIssuerFromStore(certificate.getIssuerSerialNumber());
            return id;
        }

    }

    @Override
    public String check(String id) {

        List<X509Certificate> revoked = readRevoked();
        for (X509Certificate cert : revoked) {
            if (cert.getSerialNumber().toString().equals(id)) {
                return "revoked";
            }
        }
        X509Certificate certificate = keyStoreService.getCertificate(id);
        if (certificate == null) {
            return "undefined";
        }

        return "good";
    }

    @Override
    public String download(String id) {
        X509Certificate cert = keyStoreService.getCertificate(id);
        if (cert == null) {
            return null;
        }
        StringWriter sw = new StringWriter();

        try {
            sw.write("-----BEGIN CERTIFICATE-----\n");
            sw.write(DatatypeConverter.printBase64Binary(cert.getEncoded()).replaceAll("(.{64})", "$1\n"));
            sw.write("\n-----END CERTIFICATE-----\n");
        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }

        return sw.toString();
    }


    private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void revoke(String id) {
        X509Certificate certificate = keyStoreService.getCertificate(id);
        List<X509Certificate> certificates = new ArrayList<>();

        try {
            File file = new File("./revocation.crl");

            if (!file.exists()) {
                saveCRL(certificates, file);
            } else {
                ObjectInputStream iis = new ObjectInputStream(new FileInputStream(file));
                certificates = (List<X509Certificate>) iis.readObject();
                iis.close();
            }

            for (X509Certificate cert : certificates) {
                if (cert.getSerialNumber().equals(certificate.getSerialNumber())) {
                    return;
                }
            }

            String issuerSN = new CertificateDTO(certificate).getSerialNumber();
            List<X509Certificate> allCertificates = keyStoreService.getCertificates();

            List<X509Certificate> revokeList = new ArrayList<>();
            for (X509Certificate cert : allCertificates) {
                if (new CertificateDTO(cert).getIssuerSerialNumber().equals(issuerSN)) {
                    revokeList.add(cert);
                }
            }

            allCertificates.removeAll(revokeList);
            revokeRecursion(certificates, revokeList, allCertificates);
            certificates.add(certificate);
            certificates.addAll(revokeList);
            keyStoreService.deleteList(certificates);
            saveCRL(certificates, file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void saveCRL(List<X509Certificate> certificates, File file) throws Exception {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(certificates);
        oos.flush();
        oos.close();
    }

    public void revokeRecursion(List<X509Certificate> certificates, List<X509Certificate> revokeList, List<X509Certificate> allCertificates) {
        List<X509Certificate> childRevokeList = new ArrayList<>();
        for (X509Certificate cert : revokeList) {
            CertificateDTO certDTO = new CertificateDTO(cert);
            for (X509Certificate cert1 : allCertificates) {
                if (new CertificateDTO(cert1).getIssuerSerialNumber().equals(certDTO.getSerialNumber())) {
                    childRevokeList.add(cert1);
                }
            }
        }
        if (childRevokeList.size() == 0) {
            return;
        }
        certificates.addAll(childRevokeList);
        allCertificates.removeAll(childRevokeList);
        revokeRecursion(certificates, childRevokeList, allCertificates);
    }

    @Override
    public List<X509Certificate> readRevoked() {
        List<X509Certificate> certificates = new ArrayList<>();
        File file = new File("./revocation.crl");
        ObjectInputStream iis = null;
        try {
            iis = new ObjectInputStream(new FileInputStream(file));
            certificates = (List<X509Certificate>) iis.readObject();
            iis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        return certificates;
    }
}
