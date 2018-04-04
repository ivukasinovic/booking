package XMLandSecurity.backend1.model.dto;

import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x500.style.IETFUtils;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;

import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * Created by Ivan V. on 03-Apr-18
 */
public class CertificateDTO {
    private String commonName;
    private String surname;
    private String orgName;//organisation
    private String orgNameUnit; //Locality
    private String givenName;
    private String country;
    private String email;
    private boolean isCa;

    private String uid;
    private String serialNumber;
    private Date endDate;
    private Date startDate;

    private String issuerSerialNumber;// za AIA i CDP
    private String issuerName;// preuzeto iz ComboBox-a, ako je prazno  + CA = true znaci da je on issuer

    public CertificateDTO() {
    }

    public CertificateDTO(Certificate cert)
    {

        try {
            X500Name subjName = new JcaX509CertificateHolder((X509Certificate) cert).getSubject();

            RDN cn = subjName.getRDNs(BCStyle.CN)[0];
            String cname = IETFUtils.valueToString(cn.getFirst().getValue());
            this.commonName =cname;

            RDN sn = subjName.getRDNs(BCStyle.SURNAME)[0];
            String sname = IETFUtils.valueToString(sn.getFirst().getValue());
            this.surname = sname;

            RDN on = subjName.getRDNs(BCStyle.O)[0];
            String oname = IETFUtils.valueToString(on.getFirst().getValue());
            this.orgName = oname;

            RDN oun = subjName.getRDNs(BCStyle.OU)[0];
            String ouname = IETFUtils.valueToString(oun.getFirst().getValue());
            this.orgNameUnit = ouname;

            RDN con = subjName.getRDNs(BCStyle.C)[0];
            String conname = IETFUtils.valueToString(con.getFirst().getValue());
            this.country = conname;

            RDN givn = subjName.getRDNs(BCStyle.GIVENNAME)[0];
            String givname = IETFUtils.valueToString(givn.getFirst().getValue());
            this.givenName = givenName;

            RDN en = subjName.getRDNs(BCStyle.E)[0];
            String emname = IETFUtils.valueToString(en.getFirst().getValue());
            this.email = emname;

            RDN uidn = subjName.getRDNs(BCStyle.UID)[0];
            String uidname = IETFUtils.valueToString(uidn.getFirst().getValue());
            this.uid = uidname;


            this.startDate = ((X509Certificate) cert).getNotBefore();
            this.endDate = ((X509Certificate) cert).getNotAfter();




        } catch (CertificateEncodingException e) {
            e.printStackTrace();
        }


    }


    public CertificateDTO(String commonName, String surname, String orgName, String orgNameUnit, String givenName, String country, String email, boolean isCa, String uid, String serialNumber, Date endDate, Date startDate, String issuerSerialNumber, String issuerName) {
        this.commonName = commonName;
        this.surname = surname;
        this.orgName = orgName;
        this.orgNameUnit = orgNameUnit;
        this.givenName = givenName;
        this.country = country;
        this.email = email;
        this.isCa = isCa;
        this.uid = uid;
        this.serialNumber = serialNumber;
        this.endDate = endDate;
        this.startDate = startDate;
        this.issuerSerialNumber = issuerSerialNumber;
        this.issuerName = issuerName;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgNameUnit() {
        return orgNameUnit;
    }

    public void setOrgNameUnit(String orgNameUnit) {
        this.orgNameUnit = orgNameUnit;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCa() {
        return isCa;
    }

    public void setCa(boolean ca) {
        isCa = ca;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getIssuerSerialNumber() {
        return issuerSerialNumber;
    }

    public void setIssuerSerialNumber(String issuerSerialNumber) {
        this.issuerSerialNumber = issuerSerialNumber;
    }

    public String getIssuerName() {
        return issuerName;
    }

    public void setIssuerName(String issuerName) {
        this.issuerName = issuerName;
    }
}
