package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Reservation;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.service.ReservationService;
import XMLandSecurity.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createReservation(@RequestBody Reservation reservation) {
        if (reservationService.save(reservation) != null)
            return new ResponseEntity(HttpStatus.OK);

        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Reservation> getReservation (@PathVariable("id") Long id) {
        Reservation listaAdminaFanZone = reservationService.findOne(id) ; //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Reservation> createReservation (@RequestBody Reservation res) {
        Reservation userNew = reservationService.save(res);
        return new ResponseEntity(userNew, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Reservation> updateReservation (@PathVariable("id") Long id) {
        Reservation listaAdminaFanZone = reservationService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Reservation> deleteReservation(@PathVariable("id") Long id){
        reservationService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/getReservations",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Reservation>> getLodgings(){
        List<Reservation> reservations = reservationService.findAll();
        return new ResponseEntity(reservations, HttpStatus.OK);
    }
    @RequestMapping(
            value = "/getReservationByLodging/{idLodg}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@PathVariable("idLodg") Long idLodg){

        List<Reservation> reservations = reservationService.findByLodging(idLodg);

        return new ResponseEntity(reservations, HttpStatus.OK);
    }
}