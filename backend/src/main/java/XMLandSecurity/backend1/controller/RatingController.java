package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Comment;
import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.domain.Rating;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.model.dto.CommentCloud;
import XMLandSecurity.backend1.service.LodgingService;
import XMLandSecurity.backend1.service.RatingService;
import XMLandSecurity.backend1.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
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

    @RequestMapping(value = "/{idLodging}/{star}",
            method = RequestMethod.POST)
    public ResponseEntity createRating(@RequestBody Comment comment, @PathVariable("idLodging") Long idLodging, @PathVariable("star") Long star, Principal principal) {

        Lodging lodging = lodgingService.findOne(idLodging);
        comment.setLodging(lodging);

        User loggedUser = userService.findByUsername(principal.getName());
        comment.setUser(loggedUser);

        //ratingService.save(rating); cuvanje preko cloud-a cemo raditi


        CommentCloud commentCloud = new CommentCloud(comment.getBody(), comment.getAccepted(), comment.getUser().getId(), comment.getLodging().getId(), new Date(), star);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
        try {
            json = ow.writeValueAsString(commentCloud);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RestTemplate restTemplate = new RestTemplate();
        String path = "http://localhost:8010/cloud-demo/us-central1/newRating/";
        //String url=path+"set";
        String url = path;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        // System.out.println(answer);


        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/newStar",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> proba(@RequestBody Rating ratingFront) {
        Rating rating = new Rating();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = "{\"queriedQuestion\":\"Is there pain in your hand?\"}";
        try {
            json = ow.writeValueAsString(ratingFront);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        RestTemplate restTemplate = new RestTemplate();
        String path = "http://localhost:8010/cloud-demo/us-central1/newRating/";
        String url = path + "set";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(json, headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        System.out.println(answer);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            value = "/ppp",
            method = RequestMethod.GET)
    public void redirectToTwitter(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect("http://localhost:8010/cloud-demo/us-central1/newRating?lodgingID=3");
    }
}
