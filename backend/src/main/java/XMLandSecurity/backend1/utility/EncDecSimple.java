package XMLandSecurity.backend1.utility;

import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Ivan V. on 20-May-18
 */
@Component
public class EncDecSimple {
    public static final String KEY = "KnhdV7lDmL8/ZcaiQyzCeg==";

    public byte[] encrypt(byte[] plainText) {
        try {
            //Kada se definise sifra potrebno je navesti njenu konfiguraciju, sto u ovom slucaju ukljucuje:
            //	- Algoritam koji se koristi (DES)
            //	- Rezim rada tog algoritma (ECB)
            //	- Strategija za popunjavanje poslednjeg bloka (PKCS5Padding)
            Cipher desCipherEnc = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //inicijalizacija za sifrovanje,
            byte[] decodedKey = Base64.getDecoder().decode(KEY);
            SecretKey key = new SecretKeySpec(decodedKey,0,decodedKey.length,"AES");
            desCipherEnc.init(Cipher.ENCRYPT_MODE, key);

            //sifrovanje
            byte[] ciphertext = desCipherEnc.doFinal(plainText);
            System.out.println(ciphertext);
            return ciphertext;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public byte[] decrypt(byte[] cipherText) {
        try {
            //algoritam MORA biti isti kao i kod sifrovanja, provider moze da se razlikuje
            Cipher desCipherDec = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //inicijalizacija za dekriptovanje
            byte[] decodedKey = Base64.getDecoder().decode(KEY);
            Key key = new SecretKeySpec(decodedKey,"AES");
            desCipherDec.init(Cipher.DECRYPT_MODE, key);

            //desifrovanje
            System.out.println(cipherText);
            byte[] plainText = desCipherDec.doFinal(cipherText);
            return plainText;
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | IllegalStateException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
