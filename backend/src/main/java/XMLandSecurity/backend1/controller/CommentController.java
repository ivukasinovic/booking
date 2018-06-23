package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Comment;
import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.domain.Rating;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.service.CommentService;
import XMLandSecurity.backend1.service.LodgingService;
import XMLandSecurity.backend1.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private LodgingService lodgingService;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> getComment(@PathVariable("id") Long id) {
        Comment comment = commentService.findOne(id);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{idLod}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> createComment(@PathVariable("idLod") Long idLod, @RequestBody Comment comment, Principal principal) {
        User loggedUser = userService.findByUsername(principal.getName());
        comment.setUser(loggedUser);
        Lodging lodging = lodgingService.findOne(idLod);
        comment.setLodging(lodging);

       // Comment commentNew = commentService.save(comment); Preko cloud-a dodajemo

        return new ResponseEntity(HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> updateComments(@PathVariable("id") Long id) {
        Comment comment = commentService.findOne(id);
        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") Long id) {
        Comment comment = commentService.findOne(id);
        commentService.delete(comment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Lista komentara
    @RequestMapping(
            value = "/all-not",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Comment>> pronadjiSveKojiNisuOdobreni(){
        List<Comment> listaKomentara = commentService.findByAccepted(false);
        return new ResponseEntity(listaKomentara, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/prihvati/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> prihvati(@PathVariable("id") Long id) {
        Comment komentar = commentService.findOne(id) ; //findOne(user);
        komentar.setAccepted(true);
        commentService.save(komentar);
        return new ResponseEntity<>(komentar, HttpStatus.OK);     // "200 OK"
    }
    @RequestMapping(
            value = "/all-yes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<Comment>> pronadjiSveKojiSuOdobreni(){
        List<Comment> listaKomentara = commentService.findByAccepted(true);
        return new ResponseEntity(listaKomentara, HttpStatus.OK);
    }

    @RequestMapping(value = "/newCommentCloud",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> proba( @RequestBody Comment comment) {


        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json="{\"queriedQuestion\":\"Is there pain in your hand?\"}";
        try {
            json = ow.writeValueAsString(comment);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        RestTemplate restTemplate = new RestTemplate();
        String path = "http://localhost:8010/cloud-demo/us-central1/newComment/";
        String url=path+"set";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(json,headers);
        String answer = restTemplate.postForObject(url, entity, String.class);
        System.out.println(answer);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
