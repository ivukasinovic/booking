package XMLandSecurity.backend1;

import XMLandSecurity.backend1.certificate.CertificateGenerator;
import XMLandSecurity.backend1.model.IssuerData;

import XMLandSecurity.backend1.model.SubjectData;
import XMLandSecurity.backend1.model.dto.CertificateDTO;
import XMLandSecurity.backend1.service.impl.KeyStoreImpl;

import java.security.Security;
import java.security.cert.X509Certificate;

public class probaGenerisanje {

/* TODO: ovdje sam napravila neki sertifikat, zakucala parametre da bih isprobala za keystore, zakucala sam i putanju, promjenite to
 ako stavim issuerName:"" onda je selfsigned i upise i radi sve
 ako stavim issuerName da je neki sertifkat koji postoji u keystoru onda ne radi, jer ne radi ona metoda readIsuer u keyStoreUtility
*/


    public static void main(String[] args) {
        // public CertificateDTO(String commonName, String surname, String orgName, String orgNameUnit, String givenName, String country, String email, boolean isCa, String uid, String serialNumber, Date endDate, Date startDate, String issuerSerialNumber, String issuerName) {

       /* CertificateDTO certificateDTO = new CertificateDTO("bla", "bla", "bla", "bla", "bla", "bla", "bla", true, "bla", "bla", null, null, "", "");
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        // Serijski broj sertifikata
        int randomNum = 0 + (int) (Math.random() * 10000000);
        String sn = String.valueOf(randomNum);

        certificateDTO.setSerialNumber(sn);


        SubjectData sd = confSubjectData.newSubjectData(certificateDTO);
        IssuerData id = confIssuerData.newIssuerData(certificateDTO);


        CertificateGenerator generator = new CertificateGenerator();
        X509Certificate certificate = generator.generateCertificate(sd, id);

        //novi deo
        KeyStoreImpl ks = new KeyStoreImpl();
        ks.writeCertificate("test", "test", certificate, sn, sd.getPrivateKey());
        IssuerData issuerData = ks.readIssuerFromStore("test", "test", "950029");
        System.out.println(issuerData.getX500name());
    */
    }
}
