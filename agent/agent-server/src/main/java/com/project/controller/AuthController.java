package com.project.controller;

import com.project.model.AuthenticationRequest;
import com.project.model.User;
import com.project.services.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Ivan V. on 30-May-18
 */
@RestController
public class AuthController {


    @Autowired
    private RepositoryService services;

    @RequestMapping(method = RequestMethod.POST,
            value = "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = services.findByUsername(authenticationRequest.getUsername());
        if(user != null){
            if(user.getPassword().equals(authenticationRequest.getPassword())){
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
