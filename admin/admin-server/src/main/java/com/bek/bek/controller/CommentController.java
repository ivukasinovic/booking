package com.bek.bek.controller;

import com.bek.bek.domain.Comment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object> responseEntity = rt.getForEntity("http://localhost:8080/api/comment/"+id, Object.class,id);
        Object object = responseEntity.getBody();


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public void CreateCity (@RequestBody Comment comment) {
        RestTemplate rt = new RestTemplate();
        Comment response = rt.postForObject("http://localhost:8080/api/comment",comment,Comment.class);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void izbrisi(@PathVariable("id") Long id){
        RestTemplate rt = new RestTemplate();
     rt.delete("http://localhost:8080/api/comment/"+id);

    }

    // Lista komentara
    @RequestMapping(
            value = "/all-not",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> pronadjiSveKojiNisuOdobreni(){

        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object[]> responseEntity = rt.getForEntity("http://localhost:8080/api/comment/all-not", Object[].class);
        Object[] objects = responseEntity.getBody();
        return new ResponseEntity<>(objects,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/prihvati/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> prihvati(@PathVariable("id") Long id) {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object> responseEntity = rt.getForEntity("http://localhost:8080/api/comment/prihvati/"+id, Object.class,id);
        Object object = responseEntity.getBody();


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);     // "200 OK"
    }


}
