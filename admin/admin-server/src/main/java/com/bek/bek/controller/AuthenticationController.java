package com.bek.bek.controller;//package bekend.adminpanel.controller;

import com.bek.bek.domain.User;
import com.bek.bek.model.json.request.AuthenticationRequest;
import com.bek.bek.model.json.response.AuthenticationResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthenticationController {

    private final String port = "https://localhost:8443";

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest){
        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(authenticationRequest);
        RestTemplate rt = new RestTemplate();
        AuthenticationResponse response = rt.postForObject(port + "/api/login",request,AuthenticationResponse.class);
        return ResponseEntity.ok(new AuthenticationResponse(response.getToken()));
    }

    @RequestMapping( method = RequestMethod.GET, value = "${route.authentication.refresh}")
    public void authenticationRequestRefresh(HttpServletResponse httpServletResponse,HttpServletRequest request) throws IOException {
        RestTemplate rt = new RestTemplate();

        httpServletResponse.sendRedirect(port + "/refresh}");

    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST)
    public  ResponseEntity<?> register(@RequestBody User user) {
        HttpEntity<User> request = new HttpEntity<>(user);
        RestTemplate rt = new RestTemplate();
        User response = rt.postForObject(port + "/api/login",user,User.class );

        rt.postForLocation(port + "/api/register",response);
        return ResponseEntity.ok(new User());
    }

    @RequestMapping(
            value = "/registerAgent",
            method = RequestMethod.POST)
    public void registerAgent(@RequestBody User user) {
        //return "redirect:https://localhost:8443/registerAgent" + user;
        RestTemplate rt = new RestTemplate();
       User response = rt.postForObject(port + "/api/registerAgent",user,User.class);
    }


}