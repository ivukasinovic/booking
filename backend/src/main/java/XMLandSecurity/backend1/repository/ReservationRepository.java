package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    List<Reservation> findByUser(Long id);     // andActive mozda ???!!

    Reservation save(Reservation reservation);

    List<Reservation> findByLodging(Lodging lodging);

    List<Reservation> findByLodgingId(Long id);

}
