package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
