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
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.Date;

@Service
public class CertificateImpl implements CertificateService {
    @Autowired
    private KeyStoreService keyStoreService;

    @Override
    public Certificate generateCertificate(CertificateDTO certificateDTO) {
        // Serijski broj sertifikata
        int randomNum = 0 + (int) (Math.random() * 10000000);
        String sn = String.valueOf(randomNum);

        certificateDTO.setSerialNumber(sn);

        SubjectData sd = newSubjectData(certificateDTO);
        IssuerData id = newIssuerData(certificateDTO);


        CertificateGenerator generator = new CertificateGenerator();
        X509Certificate certificate = null;
        try {
            certificate = generator.generateCertificate(sd, id , certificateDTO.isCa());
        } catch (CertIOException e) {
            e.printStackTrace();
        }
        keyStoreService.writeCertificate("test", "test",certificate, certificate.getSerialNumber().toString(), sd.getPrivateKey());

        return null;
    }

    public SubjectData newSubjectData(CertificateDTO certificate) {


        KeyPair keyPairSubject = generateKeyPair();

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
        builder.addRDN(BCStyle.UID, certificate.getUid());

        //Kreiraju se podaci za sertifikat, sto ukljucuje:
        // - javni kljuc koji se vezuje za sertifikat
        // - podatke o vlasniku
        // - serijski broj sertifikata
        // - od kada do kada vazi sertifikat
        return new SubjectData(keyPairSubject.getPublic(), keyPairSubject.getPrivate(), builder.build(), sn, startDate, endDate);


    }




    public IssuerData newIssuerData(CertificateDTO certificate) {
        String keyStoreName = "test";
        String keyStorePw = "test";
        // KeyPair keyPairSubject = generateKeyPair();


        Date startDate = null;
        //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        KeyPair keyPairIssuer = generateKeyPair();
        if (certificate.getIssuerSerialNumber().equals("")) { //ako uzmemo u obzir da ce biti prazan string ako zeli issuera da doda


            startDate = certificate.getStartDate();
            Date endDate = certificate.getStartDate();

            //Serijski broj sertifikata
            String sn = certificate.getSerialNumber();

            builder.addRDN(BCStyle.CN, certificate.getCommonName());
            builder.addRDN(BCStyle.SURNAME, certificate.getSurname());
            builder.addRDN(BCStyle.GIVENNAME, certificate.getGivenName());
            builder.addRDN(BCStyle.O, certificate.getOrgName());
            builder.addRDN(BCStyle.OU, certificate.getOrgNameUnit());
            builder.addRDN(BCStyle.C, certificate.getCountry());
            builder.addRDN(BCStyle.E, certificate.getEmail());
            builder.addRDN(BCStyle.UID, certificate.getUid());

            return new IssuerData(keyPairIssuer.getPrivate(), builder.build());
        } else {
            IssuerData id = keyStoreService.readIssuerFromStore(keyStoreName, keyStorePw, certificate.getIssuerSerialNumber());
            return id;
        }


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
}
