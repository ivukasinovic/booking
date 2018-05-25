package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.AdditionalService;
import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.service.AdditionalServiceService;
import XMLandSecurity.backend1.service.LodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/addtional-service")
public class AdditionalServiceController {

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @Autowired
    private LodgingService lodgingService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AdditionalService>> getAll() {
        List<AdditionalService> lista = additionalServiceService.findAll() ; //findOne(user);
        return new ResponseEntity<>(lista, HttpStatus.OK);     // "200 OK"
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalService> getUser(@PathVariable("id") Long id) {
        AdditionalService listaAdminaFanZone = additionalServiceService.findOne(id) ; //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    // ===


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalService> napravi (@RequestBody AdditionalService additionalService) {
       // AdditionalService noviDodati = additionalServiceService.save(additionalService);
        List<Lodging> lodgings =  lodgingService.findByAdditionalServiceList(1l); // Znaci da dodaje na prvi lodging !!!
        additionalService.setLodgingList(lodgings);

        // lodgingService.save(noviDodati.getLodgingList(lodgings));
        additionalServiceService.save(additionalService);
        return new ResponseEntity(additionalService, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalService> updateUsers (@PathVariable("id") Long id) {
        AdditionalService listaAdminaFanZone = additionalServiceService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<AdditionalService> izbrisi(@PathVariable("id") Long id){
        additionalServiceService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
