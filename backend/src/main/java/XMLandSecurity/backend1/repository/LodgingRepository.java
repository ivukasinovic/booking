package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface LodgingRepository extends JpaRepository<Lodging,Long> {


    Lodging findByAgent(Long id);

    List<Lodging>findByCityNameIgnoreCaseContainingAndPersonsNumberAndTypeLabelIgnoreCaseContainingAndCategoryLabelIgnoreCaseContaining(String name, Integer persons_number, String typeLodging, String catLodging);

    List<Lodging> findByReservationsDateStartBetweenAndReservationsDateEndBetween(Date dateStart,Date dateEnd,Date dateStart1,Date dateEnd1);
    Lodging findByTitle(String title);

   // List<Lodging> findByAdditionalServiceList(Long id);

}
