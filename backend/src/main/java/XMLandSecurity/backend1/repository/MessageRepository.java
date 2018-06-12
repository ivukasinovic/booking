package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findBySender_Id(Long id);

    List<Message> findByReceiver_Id(Long id);

}
