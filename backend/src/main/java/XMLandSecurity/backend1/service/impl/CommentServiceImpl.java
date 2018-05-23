package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Comment;
import XMLandSecurity.backend1.repository.CommentRepository;
import XMLandSecurity.backend1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findOne(Long id) {
        return commentRepository.findOne(id);
    }

    @Override
    public Comment save(Comment user) {
        return commentRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        commentRepository.delete(id);
    }


}
