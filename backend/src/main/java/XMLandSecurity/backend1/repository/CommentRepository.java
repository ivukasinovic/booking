package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByAccepted(boolean prihvacen);

    List<Comment> findByUser(Long id);


}
