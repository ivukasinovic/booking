package com.project.utility;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.xml.security.encryption.EncryptedData;
import org.apache.xml.security.encryption.EncryptedKey;
import org.apache.xml.security.encryption.XMLCipher;
import org.apache.xml.security.encryption.XMLEncryptionException;
import org.apache.xml.security.keys.KeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLEncryptionUtility {
    public XMLEncryptionUtility() {
        Security.addProvider(new BouncyCastleProvider());
        org.apache.xml.security.Init.init();
    }
	
	public SecretKey generateDataEncryptionKey() {
        try {
			KeyGenerator keyGenerator = KeyGenerator.
					getInstance("DESede");
			return keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
        return null;
    }
	
	public Document encrypt(Document doc, SecretKey key, Certificate certificate) {
		try {
		    XMLCipher xmlCipher = XMLCipher.getInstance(XMLCipher.TRIPLEDES);
		    xmlCipher.init(XMLCipher.ENCRYPT_MODE, key);
		    
			XMLCipher keyCipher = XMLCipher.getInstance(XMLCipher.RSA_v1dot5);
		    keyCipher.init(XMLCipher.WRAP_MODE, certificate.getPublicKey());
		    EncryptedKey encryptedKey = keyCipher.encryptKey(doc, key);
		    
		    EncryptedData encryptedData = xmlCipher.getEncryptedData();
		    KeyInfo keyInfo = new KeyInfo(doc);
		    keyInfo.addKeyName("Sifrovan tajni kljuc");
		    keyInfo.add(encryptedKey);
	        encryptedData.setKeyInfo(keyInfo);
			
			NodeList invoices = doc.getElementsByTagName("invoice");
			Element invoice = (Element) invoices.item(0);
			
			xmlCipher.doFinal(doc, invoice, true);
	
			return doc;
		} catch (XMLEncryptionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Document decrypt(Document doc, PrivateKey privateKey) {
		try {
			XMLCipher xmlCipher = XMLCipher.getInstance();
			xmlCipher.init(XMLCipher.DECRYPT_MODE, null);
			xmlCipher.setKEK(privateKey);
			
			NodeList encDataList = doc.getElementsByTagNameNS(
					"http://www.w3.org/2001/04/xmlenc#", 
					"EncryptedData");
			Element encData = (Element) encDataList.item(0);
			xmlCipher.doFinal(doc, encData); 
			return doc;
		} catch (XMLEncryptionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
