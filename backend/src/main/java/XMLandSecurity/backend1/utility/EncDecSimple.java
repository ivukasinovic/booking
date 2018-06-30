package XMLandSecurity.backend1.utility;

import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
    public static final String KEY = "Bar12345Bar12345";

    public static String encrypt(String text) {
        Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = null;
        byte[] encrypted = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            encrypted = cipher.doFinal(text.getBytes());
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        Base64.Encoder encoder = Base64.getEncoder();
        String encryptedString = encoder.encodeToString(encrypted);
        System.out.println(encryptedString);
        return encryptedString;
    }

    public static String decrypt(String encText) {
        Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        Base64.Decoder decoder = Base64.getDecoder();

        String decrypted = null;
        try {
            decrypted = new String(cipher.doFinal(decoder.decode(encText)));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return decrypted;
    }


}
