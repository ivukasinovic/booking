package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.*;
import XMLandSecurity.backend1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan V. on 19-May-18
 */
@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

//    @Autowired
//    private CommentService commentService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> listaAdminaFanZone = userService.findAll();
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/samo",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<ArrayList<User>> getAllUsuallyUsers() {
        List<User> listausera = userService.findAll();
        ArrayList<User> SAMO = new ArrayList<>();

        for(int i=0;i< listausera.size();i++){
            if(listausera.get(i).getRole().equals(Role.USER)){
                SAMO.add(listausera.get(i));
            }
        }

        return new ResponseEntity<>(SAMO, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User listaAdminaFanZone = userService.findOne(id);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> CreateUser (@RequestBody User user) {
        User userNew = userService.save(user);
        return new ResponseEntity(userNew, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> updateUsers (@PathVariable("id") Long id) {
        User listaAdminaFanZone = userService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<User> izbrisi(@PathVariable("id") Long id){
//        List<Comment> comment = commentService.findByUser(id);
//        for(int i=0;i< comment.size();i++){
//            // comment.remove(i);
//            commentService.delete(comment.get(i));
//        }
//
//        if( userService.findOne(id).getRole().equals("AGENT") ){
//            Lodging lodging =  lodgingService.findByAgent(id);
//            lodgingService.delete(lodging.getId());
//        }

        if(messageService.findBySender(id) != null) {
            List<Message> sender = messageService.findBySender(id);

            for(int i=0;i< sender.size();i++){
                messageService.delete(sender.get(i).getSender().getId());  // posto je i int
            }
        }
        if(messageService.findByReceiver(id) != null) {
            List<Message> reciver = messageService.findByReceiver(id);
            for (int i = 0; i < reciver.size(); i++) {
                messageService.delete(reciver.get(i).getReceiver().getId());  // posto je i int
            }
        }

        if(reservationService.findByUser(id) != null) {
            List<Reservation> reservations = reservationService.findByUser(id);
            for (int i = 0; i < reservations.size(); i++) {
                reservationService.delete(reservations.get(i).getUser().getId());  // posto je i int
            }
        }

        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "activate/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> activate(@PathVariable("id") Long id) {
        User korisnik = userService.findOne(id);
        korisnik.setActivated(true);
        userService.save(korisnik);
        return new ResponseEntity(korisnik, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "deactive/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<User> deactivate(@PathVariable("id") Long id) {
        User korisnik = userService.findOne(id);
        korisnik.setActivated(false);
        userService.save(korisnik);
        return new ResponseEntity(korisnik, HttpStatus.OK);     // "200 OK"
    }

}
