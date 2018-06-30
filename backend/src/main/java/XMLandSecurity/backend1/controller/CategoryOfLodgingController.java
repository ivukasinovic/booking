package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.CategoryOfLodging;
import XMLandSecurity.backend1.service.CategoryOfLodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/category-lodging")
public class CategoryOfLodgingController {

    @Autowired
    private CategoryOfLodgingService categoryOfLodgingService;

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<CategoryOfLodging>> getAll() {
        List<CategoryOfLodging> listaAdminaFanZone = null;
        try {
            listaAdminaFanZone = categoryOfLodgingService.findAll(); //findOne(user);
        } catch (Exception e) {
            return new ResponseEntity<List<CategoryOfLodging>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaAdminaFanZone != null) {
            return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
        } else {
            return new ResponseEntity<List<CategoryOfLodging>>(HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryOfLodging> getUser(@PathVariable("id") Long id) {
        CategoryOfLodging listaAdminaFanZone = null;

        try {
            listaAdminaFanZone = categoryOfLodgingService.findOne(id); //findOne(user);
        } catch (Exception e) {
            return new ResponseEntity<CategoryOfLodging>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaAdminaFanZone != null) {
            return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
        } else {
            return new ResponseEntity<CategoryOfLodging>(HttpStatus.NO_CONTENT);
        }

//        listaAdminaFanZone = categoryOfLodgingService.findOne(id) ; //findOne(user);

    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryOfLodging> CreateCity(@RequestBody CategoryOfLodging categoryOfLodging) throws IOException {

        CategoryOfLodging userNew = null;

        try {
            userNew = categoryOfLodgingService.save(categoryOfLodging);
        } catch (Exception e) {
            XMLandSecurity.backend1.logger.Logger.getInstance().logError(" ,Nije dodata kategorija: " + "  " + new Date());
            return new ResponseEntity<CategoryOfLodging>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (userNew != null) {
            XMLandSecurity.backend1.logger.Logger.getInstance().log(" ,Dodata kategorija: " + categoryOfLodging.getLabel() + "  " + new Date());
            return new ResponseEntity<>(userNew, HttpStatus.OK);     // "200 OK"
        } else {

            return new ResponseEntity<CategoryOfLodging>(HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryOfLodging> updateUsers(@PathVariable("id") Long id) throws IOException {

        CategoryOfLodging listaAdminaFanZone = null;

        try {
            listaAdminaFanZone = categoryOfLodgingService.findOne(id); //findOne(user);
        } catch (Exception e) {
            return new ResponseEntity<CategoryOfLodging>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (listaAdminaFanZone != null) {
            XMLandSecurity.backend1.logger.Logger.getInstance().log(" ,Izbrisana kategorija: " + listaAdminaFanZone.getLabel() + "  " + new Date());
            return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
        } else {
            return new ResponseEntity<CategoryOfLodging>(HttpStatus.NO_CONTENT);
        }

    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryOfLodging> izbrisi(@PathVariable("id") Long id) {
        //    categoryOfLodgingService.delete(id);
        try {
            categoryOfLodgingService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<CategoryOfLodging>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
