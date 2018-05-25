package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.TypeOfLodging;
import XMLandSecurity.backend1.service.TypeOfLodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/type-lodging")
public class TypeOfLodgingController {

    @Autowired
    private TypeOfLodgingService typeOfLodgingService;


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TypeOfLodging>> getAll() {
        List<TypeOfLodging> listaAdminaFanZone = typeOfLodgingService.findAll() ; //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TypeOfLodging> getUser(@PathVariable("id") Long id) {
        TypeOfLodging listaAdminaFanZone = typeOfLodgingService.findOne(id) ; //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TypeOfLodging> CreateCity (@RequestBody TypeOfLodging categoryOfLodging) {
        TypeOfLodging userNew = typeOfLodgingService.save(categoryOfLodging);
        return new ResponseEntity(userNew, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TypeOfLodging> updateUsers (@PathVariable("id") Long id) {
        TypeOfLodging listaAdminaFanZone = typeOfLodgingService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<TypeOfLodging> izbrisi(@PathVariable("id") Long id){
        typeOfLodgingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
