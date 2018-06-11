package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.domain.PriceList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceListRepository extends JpaRepository<PriceList,Long> {

   List<PriceList> findByLodging(Long id);
}
