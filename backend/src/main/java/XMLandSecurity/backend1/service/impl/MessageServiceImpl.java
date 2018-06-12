package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Message;
import XMLandSecurity.backend1.repository.MessageRepository;
import XMLandSecurity.backend1.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public List<Message> findByReceiver_Id(Long id) {
        return messageRepository.findByReceiver_Id(id);
    }

    @Override
    public Message findOne(Long id) {
        return messageRepository.findOne(id);
    }

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public void delete(Long id) {
        messageRepository.delete(id);
    }


}
