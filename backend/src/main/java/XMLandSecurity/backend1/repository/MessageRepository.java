package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message,Long> {

}
