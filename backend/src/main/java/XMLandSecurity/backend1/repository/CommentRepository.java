package XMLandSecurity.backend1.repository;

import XMLandSecurity.backend1.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {

}
