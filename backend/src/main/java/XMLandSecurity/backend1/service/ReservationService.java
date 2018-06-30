package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Reservation;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<Reservation> findAll();

    Reservation findOne(Long id);

    Reservation save(Reservation reservation);

    void delete(Long id);

    boolean checkIfOverlapingDate(Long lodging_id, Date early, Date late);

    List<Reservation> findByLodging(Long id);

    List<Reservation> findByUser(Long id);

}
