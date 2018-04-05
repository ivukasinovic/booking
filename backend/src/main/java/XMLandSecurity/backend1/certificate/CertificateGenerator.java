package XMLandSecurity.backend1.certificate;

import XMLandSecurity.backend1.model.IssuerData;
import XMLandSecurity.backend1.model.SubjectData;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class CertificateGenerator {

    public CertificateGenerator() {
    }

    public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData, boolean isCA, String issuerID) throws CertIOException {
        try {

            Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");

            builder = builder.setProvider("BC");

            //Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
            ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

            //Postavljaju se podaci za generisanje sertifiakta
            X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
                    new BigInteger(subjectData.getSerialNumber()),
                    subjectData.getStartDate(),
                    subjectData.getEndDate(),
                    subjectData.getX500name(),
                    subjectData.getPublicKey());

            //Generise se sertifikat
            X509CertificateHolder certHolder = certGen.build(contentSigner);

            //Dodatno polje da li je CA
            certGen.addExtension(Extension.basicConstraints, false, new BasicConstraints(isCA));

            //Dodatno polje svrha upotreba
            if(isCA) {
                certGen.addExtension(Extension.keyUsage, true, new KeyUsage(KeyUsage.keyCertSign));
            }else {
                certGen.addExtension(Extension.keyUsage, true, new KeyUsage(KeyUsage.digitalSignature));
            }

            // AIA extension
            GeneralName ocspLocation = new GeneralName(6,"http://localhost:8080/api/certificates/" + issuerID);
            certGen.addExtension(Extension.authorityInfoAccess, isCA, new AuthorityInformationAccess(X509ObjectIdentifiers.ocspAccessMethod, ocspLocation));

            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            certConverter = certConverter.setProvider("BC");

            //Konvertuje objekat u sertifikat
            return certConverter.getCertificate(certHolder); } catch (CertificateEncodingException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (OperatorCreationException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
