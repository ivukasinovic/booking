package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.AdditionalService;
import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.service.AdditionalServiceService;
import org.springframework.data.jpa.repository.JpaRepository;
import XMLandSecurity.backend1.domain.City;

import java.util.Date;
import java.util.List;

public interface LodgingRepository extends JpaRepository<Lodging,Long> {


    Lodging findByAgent(Long id);

    List<Lodging>findByCityNameIgnoreCaseContainingAndPersonsNumberAndTypeLabelIgnoreCaseContainingAndCategoryLabelIgnoreCaseContaining(String name, Integer persons_number, String typeLodging, String catLodging);

    List<Lodging> findByReservationsDateStartBetweenAndReservationsDateEndBetween(Date dateStart,Date dateEnd,Date dateStart1,Date dateEnd1);

   // List<Lodging> findByAdditionalServiceList(Long id);

}
