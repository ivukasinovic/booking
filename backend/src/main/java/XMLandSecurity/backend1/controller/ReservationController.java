package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.domain.Reservation;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.repository.LodgingRepository;
import XMLandSecurity.backend1.service.EmailService;
import XMLandSecurity.backend1.service.LodgingService;
import XMLandSecurity.backend1.service.ReservationService;
import XMLandSecurity.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private LodgingService lodgingService;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @RequestMapping(value = "/{idLodging}",
            method = RequestMethod.POST)
    public ResponseEntity createReservation(@RequestBody Reservation reservation, @PathVariable("idLodging") Long idLodging, Principal principal) {


        if (reservationService.checkIfOverlapingDate(idLodging,reservation.getDateStart(),reservation.getDateEnd())) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        }

        Lodging lodging = lodgingService.findOne(idLodging);
        reservation.setLodging(lodging);



        User loggedUser = userService.findByUsername(principal.getName());
        reservation.setUser(loggedUser);
        reservationService.save(reservation);
        emailService.sendReservationDetails(loggedUser, reservation);

        return new ResponseEntity(HttpStatus.OK);


    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Reservation>> getReservations() {
        List<Reservation> reservations = reservationService.findAll();
        return new ResponseEntity<List<Reservation>>(reservations, HttpStatus.OK);
    }

    @RequestMapping(value = "/cancel/{id}",
            method = RequestMethod.GET)
    public ResponseEntity cancelReservation(@PathVariable("id") Long id, Principal principal) {
        Reservation resToCancel = reservationService.findOne(id);
        User loggedUser = userService.findByUsername(principal.getName());

        if (resToCancel.getUser().getId() == loggedUser.getId() && resToCancel.getActive() == true) {
            reservationService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
    }

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
            value = "/getReservationByLodging/{idLodg}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> search(@PathVariable("idLodg") Long idLodg){

        List<Reservation> reservations = reservationService.findByLodging(idLodg);

        return new ResponseEntity(reservations, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/check/{id}/{startDate}/{endDate}",
            method = RequestMethod.GET
    )
    public ResponseEntity checkAvaliability(@PathVariable("id")Long id, @PathVariable("startDate")String startDate,
                                            @PathVariable("endDate")String endDate){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateS = null,dateE = null;
        try {
            dateS = format.parse(startDate);
            dateE = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(reservationService.checkIfOverlapingDate(id,dateS,dateE))
            return new ResponseEntity(HttpStatus.CONFLICT);

        return  new ResponseEntity(HttpStatus.OK);
    }


}