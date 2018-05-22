package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
