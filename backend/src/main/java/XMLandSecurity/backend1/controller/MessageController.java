package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Message;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.service.MessageService;
import XMLandSecurity.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Message> getMessage(@PathVariable("id") Long id) {
        Message message = messageService.findOne(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }




    @RequestMapping(
            value = "/{idRec}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Message> sendMessage(@RequestBody Message message, @PathVariable("idRec") Long idRec, Principal principal) {
        User loggedUser = userService.findByUsername(principal.getName());
        message.setSender(loggedUser);
        User receiver = userService.findOne(idRec);
        message.setReceiver(receiver);
        message.setDateSent(new Date());
        Message messageNew = messageService.save(message);
        return new ResponseEntity(messageNew, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Message> updateMessage(@PathVariable("id") Long id) {
        Message message = messageService.findOne(id);
        return new ResponseEntity(message, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Message> deleteMessage(@PathVariable("id") Long id) {
        messageService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
