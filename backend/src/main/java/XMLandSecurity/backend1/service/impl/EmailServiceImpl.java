package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.service.EmailService;
import XMLandSecurity.backend1.utility.EncDecSimple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.mail.internet.MimeMessage;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Ivan V. on 19-May-18
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EncDecSimple encDecSimple;

    @Async
    @Override
    public void sendActivationMail(User user) {
        String encryptedString = EncDecSimple.encrypt(user.getUsername());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            String htmlMsg = "<h3>Profile activation!</h3><br>"
                    + "<div>Welcome "+user.getUsername()+"  on <b>Booking.com </b></div>"
                    + "<div>Click  <a href ="
                    + " \"http://localhost:4200/api/activate/"+encryptedString+"\">"
                    + "<u>here</u></a> for activation.</div>";
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Activation link for Booking.com");
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
        }
    }

    @Async
    @Override
    public void sendResetPassword(User user) {

        String encryptedString = EncDecSimple.encrypt(user.getUsername());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            String htmlMsg = "<h3>Reset password!</h3><br>"
                    + "<div>Hi "+user.getUsername()+", you want reset password on <b>Booking.com </b></div>"
                    + "<div>Click  <a href ="
                    + " \"http://localhost:4200/reset-password/"+encryptedString+"\">"
                    + "<u>here</u></a> to reset your password.</div>";
            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Link for reset password on Booking.com");
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
        }
    }


}
