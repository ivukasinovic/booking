package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Ivan V.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    User findByEmail(String email);
}
