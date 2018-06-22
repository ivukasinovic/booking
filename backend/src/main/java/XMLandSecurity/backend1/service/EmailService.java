package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Reservation;
import XMLandSecurity.backend1.domain.User;

/**
 * Created by Ivan V. on 19-May-18
 */
public interface EmailService {
    void sendActivationMail(User user);
    void sendResetPassword(User user);

    void sendReservationDetails(User user, Reservation reservation);
}
