package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository  extends JpaRepository<Country,Long> {


}
