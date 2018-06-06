package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.*;
import XMLandSecurity.backend1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

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
    public ResponseEntity<?> CreateUser (@Validated @RequestBody User user, Errors errors) {

        if (errors.hasErrors()) {
            return new ResponseEntity<String>(errors.getAllErrors().toString(), HttpStatus.BAD_REQUEST);
        }

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


    @RequestMapping(
            value = "/reservations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Reservation> getReservations(Principal principal) {

        User loggedUser = userService.findByUsername(principal.getName());

        List<Reservation> reservations = loggedUser.getReservations();

        return new ResponseEntity(reservations, HttpStatus.OK);
    }


}
