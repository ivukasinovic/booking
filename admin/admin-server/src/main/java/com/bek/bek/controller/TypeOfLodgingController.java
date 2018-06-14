package com.bek.bek.controller;

import com.bek.bek.domain.TypeOfLodging;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/type-lodging")
public class TypeOfLodgingController {

    private final String port = "https://localhost:8443";

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAll() {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object[]> responseEntity = rt.getForEntity(port + "/api/type-lodging", Object[].class);
        Object[] objects = responseEntity.getBody();
        return new ResponseEntity<>(objects,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object> responseEntity = rt.getForEntity(port + "/api/type-lodging/"+id, Object.class,id);
        Object object = responseEntity.getBody();


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);       // "200 OK"
    }

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<TypeOfLodging> CreateCity (@RequestBody TypeOfLodging categoryOfLodging) {
        HttpEntity<TypeOfLodging> request = new HttpEntity<>(categoryOfLodging);
        RestTemplate rt = new RestTemplate();
        TypeOfLodging response = rt.postForObject(port + "/api/type-lodging",categoryOfLodging,TypeOfLodging.class );

        return ResponseEntity.ok(new TypeOfLodging());
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void izbrisi(@PathVariable("id") Long id){

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete ( port + "/api/type-lodging/"+id);
    }

}
