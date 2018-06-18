package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Comment;
import XMLandSecurity.backend1.domain.Lodging;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.service.CommentService;
import XMLandSecurity.backend1.service.LodgingService;
import XMLandSecurity.backend1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

        Comment commentNew = commentService.save(comment);
        return new ResponseEntity(commentNew, HttpStatus.OK);
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

}
