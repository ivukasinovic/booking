package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.certificate.CertificateGenerator;
import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.SubjectData;
import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.CertificateService;
import XMLandSecurity.backend1.service.EmailService;
import XMLandSecurity.backend1.service.KeyStoreService;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMWriter;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.jcajce.JcaPKCS10CertificationRequestBuilder;
import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private KeyStoreService keyStoreService;

    private KeyPair keyPair;
    @Autowired
    private EmailService emailService;
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
        SubjectData sd = newSubjectData(certificateDTO, keyPair.getPublic());
        IssuerData id = newIssuerData(certificateDTO);


        CertificateGenerator generator = new CertificateGenerator();
        X509Certificate certificate = null;
        try {
            certificate = generator.generateCertificate(sd, id, certificateDTO.getisCa(), certificateDTO.getIssuerSerialNumber());
        } catch (CertIOException e) {
            e.printStackTrace();
        }
        keyStoreService.writeCertificate(certificateDTO.getisCa(), certificate, certificateDTO.getSerialNumber(), keyPair.getPrivate());

        return certificate;
    }

    @Override
    public PKCS10CertificationRequest generateCertificateRequest(CertificateDTO certificateDTO) {
        try {
            // Serijski broj sertifikata
            int randomNum = 0 + (int) (Math.random() * 10000000);
            String sn = String.valueOf(randomNum);
            certificateDTO.setSerialNumber(sn);

            X500NameBuilder b = new X500NameBuilder(BCStyle.INSTANCE);
            b.addRDN(BCStyle.CN, certificateDTO.getCommonName());
            b.addRDN(BCStyle.SURNAME, certificateDTO.getSurname());
            b.addRDN(BCStyle.GIVENNAME, certificateDTO.getGivenName());
            b.addRDN(BCStyle.O, certificateDTO.getOrgName());
            b.addRDN(BCStyle.OU, certificateDTO.getOrgNameUnit());
            b.addRDN(BCStyle.C, certificateDTO.getCountry());
            b.addRDN(BCStyle.E, certificateDTO.getEmail());
            b.addRDN(BCStyle.UID, certificateDTO.getSerialNumber());

            X500Name name = b.build();

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);

            KeyPair pair = keyGen.generateKeyPair();

            System.out.println(pair.getPublic());

            JcaPKCS10CertificationRequestBuilder p10Builder = new JcaPKCS10CertificationRequestBuilder(name, pair.getPublic());
            JcaContentSignerBuilder csBuilder = new JcaContentSignerBuilder("SHA256withRSA");
            ContentSigner signer = csBuilder.build(pair.getPrivate());
            PKCS10CertificationRequest request = p10Builder.build(signer);
            String data = request.getSubject().toString();
            System.out.println("CSR is: " + data);

            File f = new File("./files/csr/" + certificateDTO.getSerialNumber() + ".csr");
            BufferedWriter w = new BufferedWriter(new FileWriter(f.getPath()));
            StringWriter sw = new StringWriter();
            JcaPEMWriter pemWriter = new JcaPEMWriter(sw);
            pemWriter.writeObject(request);

            pemWriter.close();
            w.write(sw.toString());
            sw.close();
            w.flush();
            w.close();

            emailService.sendCSRDetails(certificateDTO.getEmail(), pair.getPrivate());
            return request;
        } catch (NoSuchAlgorithmException e) {
        } catch (NoSuchProviderException e) {
        } catch (OperatorCreationException e) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public List<CertificateDTO> getAllCSRs() {
        ArrayList<CertificateDTO> retVal = new ArrayList<CertificateDTO>();


        File folder = new File("./files/csr");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File[] listOfFiles = folder.listFiles();

        //iteracija kroz csr fajlove i instanciranje DTO-ova koji ce se prikazati na frontendu
        for (File f : listOfFiles) {

            BufferedReader r = null;
            try {
                r = new BufferedReader(new FileReader(f.getPath()));

                PemReader pemReader = new PemReader(r);
                PEMParser pemParser = new PEMParser(pemReader);
                Object o = pemParser.readObject();
                PKCS10CertificationRequest csr = (PKCS10CertificationRequest) o;

                CertificateDTO dto = new CertificateDTO();

                X500Name subjName = csr.getSubject();

                RDN cn = subjName.getRDNs(BCStyle.CN)[0];
                dto.setCommonName(IETFUtils.valueToString(cn.getFirst().getValue()));


                RDN sn = subjName.getRDNs(BCStyle.SURNAME)[0];
                dto.setSurname(IETFUtils.valueToString(sn.getFirst().getValue()));


                RDN on = subjName.getRDNs(BCStyle.O)[0];
                dto.setOrgName(IETFUtils.valueToString(on.getFirst().getValue()));


                RDN oun = subjName.getRDNs(BCStyle.OU)[0];
                dto.setOrgNameUnit(IETFUtils.valueToString(oun.getFirst().getValue()));


                RDN con = subjName.getRDNs(BCStyle.C)[0];
                dto.setCountry(IETFUtils.valueToString(con.getFirst().getValue()));


                RDN givn = subjName.getRDNs(BCStyle.GIVENNAME)[0];
                dto.setGivenName(IETFUtils.valueToString(givn.getFirst().getValue()));

                RDN en = subjName.getRDNs(BCStyle.E)[0];
                dto.setEmail(IETFUtils.valueToString(en.getFirst().getValue()));


                RDN uidn = subjName.getRDNs(BCStyle.UID)[0];
                dto.setUid(IETFUtils.valueToString(uidn.getFirst().getValue()));

                pemParser.close();
                pemReader.close();
                r.close();

                retVal.add(dto);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

            }

        }

        return retVal;
    }

    @Override
    public void aproveCSR(String id) {


        //preuzima se csr na osnovu prosledjenog parametra
        File f = new File("./files/csr/" + id + ".csr");
        BufferedReader r = null;
        try {
            r = new BufferedReader(new FileReader(f.getPath()));

            PemReader pemReader = new PemReader(r);
            PEMParser pemParser = new PEMParser(pemReader);
            Object o = pemParser.readObject();
            PKCS10CertificationRequest csr = (PKCS10CertificationRequest) o;
            //preuzimaje podataka iz csr za sertifikat
            CertificateDTO dto = new CertificateDTO();

            X500Name subjName = csr.getSubject();

            RDN cn = subjName.getRDNs(BCStyle.CN)[0];
            dto.setCommonName(IETFUtils.valueToString(cn.getFirst().getValue()));

            RDN sn = subjName.getRDNs(BCStyle.SURNAME)[0];
            dto.setSurname(IETFUtils.valueToString(sn.getFirst().getValue()));

            RDN on = subjName.getRDNs(BCStyle.O)[0];
            dto.setOrgName(IETFUtils.valueToString(on.getFirst().getValue()));

            RDN oun = subjName.getRDNs(BCStyle.OU)[0];
            dto.setOrgNameUnit(IETFUtils.valueToString(oun.getFirst().getValue()));

            RDN con = subjName.getRDNs(BCStyle.C)[0];
            dto.setCountry(IETFUtils.valueToString(con.getFirst().getValue()));

            RDN givn = subjName.getRDNs(BCStyle.GIVENNAME)[0];
            dto.setGivenName(IETFUtils.valueToString(givn.getFirst().getValue()));

            RDN en = subjName.getRDNs(BCStyle.E)[0];
            dto.setEmail(IETFUtils.valueToString(en.getFirst().getValue()));

            RDN uidn = subjName.getRDNs(BCStyle.UID)[0];
            dto.setUid(IETFUtils.valueToString(uidn.getFirst().getValue()));

            pemParser.close();
            pemReader.close();
            r.close();

            dto.setIsCa(false);
            //
            dto.setIssuerSerialNumber("6782828");

            dto.setSerialNumber(id);
            //public key
            SubjectPublicKeyInfo pkInfo = csr.getSubjectPublicKeyInfo();
            RSAKeyParameters rsa = (RSAKeyParameters) PublicKeyFactory.createKey(pkInfo);
            RSAPublicKeySpec rsaSpec = new RSAPublicKeySpec(rsa.getModulus(), rsa.getExponent());
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PublicKey publicKey = kf.generatePublic(rsaSpec);


            SubjectData subjectData = newSubjectData(dto, publicKey);
            IssuerData issuerData = newIssuerData(dto);

            CertificateGenerator generator = new CertificateGenerator();
            X509Certificate certificate = null;
            try {
                certificate = generator.generateCertificate(subjectData, issuerData, false, dto.getIssuerSerialNumber());
            } catch (CertIOException e) {
                e.printStackTrace();
            }

            keyStoreService.writeCertificate(dto.getisCa(), certificate, dto.getSerialNumber(), null);
            //brisanje csr zahteva
            f.delete();
            emailService.sendCSRStatus(dto.getEmail(), "aproved");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCSR(String id) {//preuzima se csr na osnovu prosledjenog parametra
        File f = new File("./files/csr/" + id + ".csr");
        BufferedReader r = null;
        try {
            r = new BufferedReader(new FileReader(f.getPath()));

            PemReader pemReader = new PemReader(r);
            PEMParser pemParser = new PEMParser(pemReader);
            Object o = pemParser.readObject();
            PKCS10CertificationRequest csr = (PKCS10CertificationRequest) o;
            //preuzimaje podataka iz csr za sertifikat

            RDN en = csr.getSubject().getRDNs(BCStyle.E)[0];
            String email = (IETFUtils.valueToString(en.getFirst().getValue()));
            emailService.sendCSRStatus(email, "denied");
            pemParser.close();
            pemReader.close();
            r.close();
            f.delete();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public SubjectData newSubjectData(CertificateDTO certificate, PublicKey publicKey) {

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
        return new SubjectData(publicKey, builder.build(), sn, startDate, endDate);

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
        if (revoked != null) {
            for (X509Certificate cert : revoked) {
                if (cert.getSerialNumber().toString().equals(id)) {
                    return "revoked";
                }
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
//            e.printStackTrace();
            return null;
        }
        return certificates;
    }
}
