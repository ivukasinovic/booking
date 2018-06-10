package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.domain.Rating;
import XMLandSecurity.backend1.domain.Reservation;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.service.LodgingService;
import XMLandSecurity.backend1.service.RatingService;
import XMLandSecurity.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
