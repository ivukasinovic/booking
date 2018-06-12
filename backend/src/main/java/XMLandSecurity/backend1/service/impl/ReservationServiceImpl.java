package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.domain.Reservation;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.repository.ReservationRepository;
import XMLandSecurity.backend1.service.LodgingService;
import XMLandSecurity.backend1.service.ReservationService;
import XMLandSecurity.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private LodgingService lodgingService;


    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation findOne(Long id) {
        return reservationRepository.findOne(id);
    }

    @Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void delete(Long id) {
        reservationRepository.delete(id);
    }

    @Override
    public boolean checkIfOverlapingDate(Long lodging_id, Date early, Date late ) {

        for (Reservation temp : reservationRepository.findByLodgingId(lodging_id) ) {
            if (!(early.after(temp.getDateEnd()) || late.before(temp.getDateStart())))
                return true;
        }
        return false;
    }


    @Override
    public List<Reservation> findByLodging(Long id) {
       return reservationRepository.findByLodging(lodgingService.findOne(id));
    }

    @Override
    public List<Reservation> findByUser(Long id) {
        return reservationRepository.findByUser(id);
    }
}
