package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.AdditionalService;
import XMLandSecurity.backend1.security.Validator;
import XMLandSecurity.backend1.service.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/addtional-service")
public class AdditionalServiceController {

    @Autowired
    private AdditionalServiceService additionalServiceService;


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AdditionalService>> getAll() {

        List<AdditionalService> lista = null;

        try {
            lista = additionalServiceService.findAll();
        }catch (Exception e){
            return new ResponseEntity<List<AdditionalService>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(lista != null) {
            return new ResponseEntity<>(lista, HttpStatus.OK);     // "200 OK"
        }else {
            return new ResponseEntity<List<AdditionalService>>(HttpStatus.NO_CONTENT);
        }

        }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalService> getUser(@PathVariable("id") Long id) {

        AdditionalService additionalService = null;

        try {
            additionalService = additionalServiceService.findOne(id);
        }catch (Exception e) {
            return new ResponseEntity<AdditionalService>(HttpStatus.INTERNAL_SERVER_ERROR);
        }


        if(additionalService == null){
            return new ResponseEntity<AdditionalService>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(additionalService, HttpStatus.OK);     // "200 OK"
        }

    }


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalService> napravi (@RequestBody AdditionalService additionalService) {

        AdditionalService additionalService12 = null;

        try {
            additionalService12 =  additionalServiceService.save(additionalService);
        } catch (Exception e){
            return new ResponseEntity<AdditionalService>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if(additionalService12 == null){
            return new ResponseEntity<AdditionalService>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(additionalService12, HttpStatus.OK);     // "200 OK"
        }

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

    public AdditionalService provera(AdditionalService additionalService){

        Set<ConstraintViolation<AdditionalService>> violations = Validator.getInstance().getValidator().validate(additionalService);

        if (!violations.isEmpty()) {

            Iterator iter = violations.iterator();

            ConstraintViolation<AdditionalService> first = (ConstraintViolation<AdditionalService>) iter.next();

            AdditionalService servis =new AdditionalService(new Long(-1), "", first.getMessage());

            return servis;

        } else {

            return null;

        }

    }



}
