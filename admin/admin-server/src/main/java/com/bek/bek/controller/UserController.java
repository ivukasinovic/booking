package com.bek.bek.controller;//package bekend.adminpanel.controller;

import com.bek.bek.domain.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final String port = "https://localhost:8443";

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> getAllUsers() {
        // return "redirect:/https://localhost:8443/users";
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object[]> responseEntity = rt.getForEntity(port+ "/api/users", Object[].class);
        Object[] objects = responseEntity.getBody();
        return new ResponseEntity<>(objects,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/samo",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAllUsuallyUsers() {
    //return "redirect:/https://localhost:8443/users/samo";
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object[]> responseEntity = rt.getForEntity(port + "/api/users/samo", Object[].class);
        Object[] objects = responseEntity.getBody();
        return new ResponseEntity<>(objects,HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public String getUser(@PathVariable("id") Long id) {
    return  "redirect:/https://localhost:8443/users/" + id;
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public String CreateUser (@RequestBody User user) {
        return  "redirect:/https://localhost:8443/users/" +  user;
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT
    )
    public String updateUsers (@PathVariable("id") Long id) {
        return  "https://localhost:8443/users/" + id;

    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void izbrisi(@PathVariable("id") Long id){
        //return  "https://localhost:8443/users/" + id;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete ( port + "/api/users/"+id, id );

    }

    @RequestMapping(
            value = "activate/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> activate(@PathVariable("id") Long id) {
       // return  "https://localhost:8443/users/activate/" + id;
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object> responseEntity = rt.getForEntity(port + "/api/users/activate/"+id, Object.class,id);
        Object object = responseEntity.getBody();


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    @RequestMapping(
            value = "deactive/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> deactivate(@PathVariable("id") Long id) {
       // return  "https://localhost:8443/users/deactive/" + id;
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object> responseEntity = rt.getForEntity(port + "/api/users/deactive//"+id, Object.class,id);
        Object object = responseEntity.getBody();


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }


}
