package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Country;
import XMLandSecurity.backend1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Country> getUser(@PathVariable("id") Long id) {
        Country listaAdminaFanZone = countryService.findOne(id); //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    // ===


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Country> CreateCity(@RequestBody Country country) {
        Country userNew = countryService.save(country);
        return new ResponseEntity(userNew, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Country> updateUsers(@PathVariable("id") Long id) {
        Country listaAdminaFanZone = countryService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Country> izbrisi(@PathVariable("id") Long id) {
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
