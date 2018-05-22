package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Message;

import java.util.List;


public interface MessageService {


    List<Message> findAll();

    Message findOne(Long id);

    Message save(Message message);

    void delete(Long id);

}
