package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import XMLandSecurity.backend1.domain.City;

import java.util.Date;
import java.util.List;

public interface LodgingRepository extends JpaRepository<Lodging,Long> {


    Lodging findByAgent(Long id);
    List<Lodging> findByCityNameIgnoreCaseContainingAndReservationsDateStartBeforeOrReservationsDateStartAfterAndReservationsDateEndBeforeOrReservationsDateEndAfter(String cityName, Date startDate, Date endDate ,Date startDate1, Date endDate1);
    List<Lodging>findByCityNameIgnoreCaseContainingAndPersonsNumber(String name, Integer persons_number);
   // List<Lodging> findByAdditionalServiceList(Long id);

  //ako se  datum pocetni iz baze nalazi izmedju dva datuma koja korisnik salje onda NE PRIKAZUJ taj smestaj
    //isto za krajnji datum
}
