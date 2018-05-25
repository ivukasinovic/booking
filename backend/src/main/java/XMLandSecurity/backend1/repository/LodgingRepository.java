package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Lodging;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LodgingRepository extends JpaRepository<Lodging,Long> {


    Lodging findByAgent(Long id);

    List<Lodging> findByAdditionalServiceList(Long id);

}
