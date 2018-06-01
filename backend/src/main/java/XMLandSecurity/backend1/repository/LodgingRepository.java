package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;
import XMLandSecurity.backend1.domain.City;
import java.util.List;

public interface LodgingRepository extends JpaRepository<Lodging,Long> {


    Lodging findByAgent(Long id);
    List<Lodging> findByCityNameIgnoreCaseContaining( String cityName);
    List<Lodging>findByCityNameIgnoreCaseContainingAndPersonsNumber(String name, Integer persons_number);
   // List<Lodging> findByAdditionalServiceList(Long id);


}
