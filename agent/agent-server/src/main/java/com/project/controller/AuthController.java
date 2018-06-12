package com.project.controller;

import com.project.model.json.AuthenticationRequest;
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

    @RequestMapping(method = RequestMethod.POST,
            value = "login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationRequest> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest) {

        return new ResponseEntity<>(authenticationRequest,HttpStatus.OK);
    }
}
