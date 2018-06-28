package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Reservation;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.service.EmailService;
import XMLandSecurity.backend1.utility.EncDecSimple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.io.StringWriter;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;

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

    @Async
    @Override
    public void sendReservationDetails(User user, Reservation reservation) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
            String htmlMsg = "<h3>You succesufully reserved " + reservation.getLodging().getTitle()
                    + " in " + reservation.getLodging().getCity().getName()
                    + " ," + reservation.getLodging().getAddress()
                    + " from " + reservation.getDateStart()
                    + " until " + reservation.getDateEnd()
                    + " on  <b>Booking.com </b>!</h3><br>";

            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(user.getEmail());
            helper.setSubject("Successfull reservation");
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Async
    @Override
    public void sendCSRDetails(String email, PrivateKey aPrivate) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            String htmlMsg = "Hi, <br/> We have received your certificate request. In attacment we are sending" +
                    " you your public/private keypair. You should keep it, and you shouldn't share it with others.<br><br>" +
                    " As soon as our admin aproves your request, we will notify you by email for futher details.<br>" +
                    " Best regards,<br> Booking.com";

            // mimeMessage.setContent(htmlMsg, "text/html");
            helper.setText(htmlMsg, true);
            helper.setTo(email);
            helper.setSubject("Ceritificate request");

            StringWriter sw = new StringWriter();

            try {
                sw.write("-----BEGIN PRIVATE KEY-----\n");
                sw.write(DatatypeConverter.printBase64Binary(aPrivate.getEncoded()).replaceAll("(.{64})", "$1\n"));
                sw.write("\n-----END PRIVATE KEY-----\n");
            } catch (Exception e) {
                e.printStackTrace();
            }


            helper.addAttachment("private key", new ByteArrayResource(sw.toString().getBytes()));
            javaMailSender.send(mimeMessage);

            System.out.println("poslao mailll");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Async
    @Override
    public void sendCSRStatus(String email, String status) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String htmlMsg = "";

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");


            if (status.equals("aproved")) {
                htmlMsg = "We're happy to inform you that you certificate is ready. You can download it now from our cite.";
            } else {
                htmlMsg = "There was problem with your request.";
            }

            mimeMessage.setContent(htmlMsg, "text/html");
            helper.setTo(email);
            helper.setSubject("CSR status");
            javaMailSender.send(mimeMessage);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
