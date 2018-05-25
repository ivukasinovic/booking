package XMLandSecurity.backend1.controller;

import XMLandSecurity.backend1.domain.Comment;
import XMLandSecurity.backend1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> getUser(@PathVariable("id") Long id) {
        Comment listaAdminaFanZone = commentService.findOne(id) ; //findOne(user);
        return new ResponseEntity<>(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    // ===


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> CreateCity (@RequestBody Comment comment) {
        Comment userNew = commentService.save(comment);
        return new ResponseEntity(userNew, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Comment> updateUsers (@PathVariable("id") Long id) {
        Comment listaAdminaFanZone = commentService.findOne(id);
        return new ResponseEntity(listaAdminaFanZone, HttpStatus.OK);     // "200 OK"
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public ResponseEntity<Comment> izbrisi(@PathVariable("id") Long id){
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


}
