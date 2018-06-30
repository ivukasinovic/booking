package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByNameIgnoreCaseContaining(String name);


}
