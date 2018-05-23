package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Comment;

import java.util.List;

public interface CommentService {


    List<Comment> findAll();

    Comment findOne(Long id);

    Comment save(Comment user);

    void delete(Long id);

    List<Comment> findByAccepted(boolean prihvacen);


}
