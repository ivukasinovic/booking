package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.*;
import XMLandSecurity.backend1.service.LodgingService;
import XMLandSecurity.backend1.service.RatingService;
import XMLandSecurity.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @Autowired
    private UserService userService;
    @Autowired
    private LodgingService lodgingService;

    @RequestMapping(value = "/{idLodging}",
            method = RequestMethod.POST)
    public ResponseEntity createRating(@RequestBody Rating rating, @PathVariable("idLodging") Long idLodging, Principal principal) {

        Lodging lodging = lodgingService.findOne(idLodging);
        rating.setLodging(lodging);

        User loggedUser = userService.findByUsername(principal.getName());
        rating.setUser(loggedUser);

        rating.setDateCreated(new Date());
        ratingService.save(rating);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/proba",
            method = RequestMethod.POST)
    public ResponseEntity<?> proba(HttpServletResponse httpServletResponse) {
        Rating rating = new Rating();
        //rating.setLodging(2);
        rating.setStar((long) 1);
        String path = "http://localhost:8010/cloud-demo/us-central1/newRating";
        RestTemplate restTemplate = new RestTemplate();
        Cloud response = restTemplate.postForObject(path, rating, Cloud.class);
        if(!response.getResult()) {
            System.out.println("Los prolazak kroz cloud");
            return new ResponseEntity<>(new Cloud(false, "Bad Request"), HttpStatus.BAD_REQUEST);
        }
        else{
            System.out.println("Dobar prolazak kroz cloud");
            return new ResponseEntity<>(new Cloud(true, "Success"), HttpStatus.OK);
        }
    }
    @RequestMapping(
            value = "/ppp",
            method = RequestMethod.GET)
    public void redirectToTwitter(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("http://localhost:8010/cloud-demo/us-central1/newRating?lodgingID=3");
    }
}
