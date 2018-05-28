package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Reservation;
import XMLandSecurity.backend1.repository.ReservationRepository;
import XMLandSecurity.backend1.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;


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

//    @Override
//    public List<Reservation> findByUser(Long id) {
//        return reservationRepository.findByUser(id);
//    }
}
