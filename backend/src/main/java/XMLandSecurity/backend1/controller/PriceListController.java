package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.PriceList;
import XMLandSecurity.backend1.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/priceList")
public class PriceListController {


    @Autowired
    private PriceListService priceListService;


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PriceList> getPriceList(@PathVariable("id") Long id) {
        PriceList listaAdminaFanZone = priceListService.findOne(id); //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PriceList> createPriceList(@RequestBody PriceList priceList) {
        PriceList userNew = priceListService.save(priceList);
        return new ResponseEntity(userNew, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PriceList> updatePriceList(@PathVariable("id") Long id) {
        PriceList listaAdminaFanZone = priceListService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PriceList> izbrisi(@PathVariable("id") Long id) {
        priceListService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(
            value = "/getPriceLists",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PriceList>> getPriceLists() {
        List<PriceList> lists = priceListService.findAll();
        return new ResponseEntity(lists, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{lodgingId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PriceList> getPriceListByLodging(@PathVariable("LodingId") Long id) {
        List<PriceList> listaAdminaFanZone = priceListService.findByLodging(id); //findOne(user);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

}
