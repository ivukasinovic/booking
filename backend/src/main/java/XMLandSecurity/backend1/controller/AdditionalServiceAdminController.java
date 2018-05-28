package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.AdditionalService;
import XMLandSecurity.backend1.domain.AdditionalServiceAdmin;
import XMLandSecurity.backend1.service.AdditionalServiceAdminService;
import XMLandSecurity.backend1.service.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/addtional-serviceadmin")
public class AdditionalServiceAdminController  {

    @Autowired
    private AdditionalServiceAdminService additionalServiceAdminService;

    @Autowired
    private AdditionalServiceService additionalServiceService;


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AdditionalServiceAdmin>> getAll() {
        List<AdditionalServiceAdmin> lista = additionalServiceAdminService.findAll() ; //findOne(user);
        return new ResponseEntity<>(lista, HttpStatus.OK);     // "200 OK"
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalServiceAdmin> getAdditional(@PathVariable("id") Long id) {
        AdditionalServiceAdmin listaAdminaFanZone = additionalServiceAdminService.findOne(id) ; //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    // ===


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalServiceAdmin> napravi (@RequestBody AdditionalServiceAdmin additionalService) {
        // AdditionalService noviDodati = additionalServiceService.save(additionalService);
//        List<Lodging> lodgings =  lodgingService.findByAdditionalServiceList(1l); // Znaci da dodaje na prvi lodging !!!
//        additionalService.setLodgingList(lodgings);

        // lodgingService.save(noviDodati.getLodgingList(lodgings));
         AdditionalService additionalService112 =  additionalServiceService.findOne(1L);
        additionalService.setAdditionalService(additionalService112);

        additionalServiceAdminService.save(additionalService);
        return new ResponseEntity(additionalService, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalServiceAdmin> updateUsers (@PathVariable("id") Long id) {
        AdditionalServiceAdmin listaAdminaFanZone = additionalServiceAdminService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<AdditionalServiceAdmin> izbrisi(@PathVariable("id") Long id){
        additionalServiceAdminService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
