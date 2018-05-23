package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.CategoryOfLodging;
import XMLandSecurity.backend1.service.CategoryOfLodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/category-lodging")
public class CategoryOfLodgingController {

    @Autowired
    private CategoryOfLodgingService categoryOfLodgingService;


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryOfLodging> getUser(@PathVariable("id") Long id) {
        CategoryOfLodging listaAdminaFanZone = categoryOfLodgingService.findOne(id) ; //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryOfLodging> CreateCity (@RequestBody CategoryOfLodging categoryOfLodging) {
        CategoryOfLodging userNew = categoryOfLodgingService.save(categoryOfLodging);
        return new ResponseEntity(userNew, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryOfLodging> updateUsers (@PathVariable("id") Long id) {
        CategoryOfLodging listaAdminaFanZone = categoryOfLodgingService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CategoryOfLodging> izbrisi(@PathVariable("id") Long id){
        categoryOfLodgingService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
