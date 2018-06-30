package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/lodging")
public class LodgingController {

    @Autowired
    private LodgingService lodgingService;
    @Autowired
    private CityService cityService;
    @Autowired
    private AdditionalServiceService additionalServiceService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Lodging> getLodging(@PathVariable("id") Long id) {
        Lodging lodging = lodgingService.findOne(id); //findOne(user);
        return new ResponseEntity<>(lodging, HttpStatus.OK);     // "200 OK"
    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Lodging> createLodging(@RequestBody Lodging lodging) throws IOException {
        Lodging lodgingNew = lodgingService.save(lodging);
        XMLandSecurity.backend1.logger.Logger.getInstance().log(" ,Dodat smestaj: " + lodging.getTitle() + " Agent: " + lodging.getAgent() + "  " + new Date());

        return new ResponseEntity(lodgingNew, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Lodging> updateLodging(@PathVariable("id") Long id) {
        Lodging lodging = lodgingService.findOne(id);
        return new ResponseEntity(lodging, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Lodging> izbrisi(@PathVariable("id") Long id) throws IOException {
        Lodging lodging = lodgingService.findOne(id); //findOne(user);
        XMLandSecurity.backend1.logger.Logger.getInstance().log(" ,Izbrisan smestaj: " + lodging.getTitle() + " Agent: " + lodging.getAgent() + "  " + new Date());


        lodgingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/getLodgings",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Lodging>> getLodgings() {
        List<Lodging> lodgings = lodgingService.findAll();
        return new ResponseEntity(lodgings, HttpStatus.OK);
    }

}
