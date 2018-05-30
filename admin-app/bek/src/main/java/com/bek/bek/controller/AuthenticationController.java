package com.bek.bek.controller;//package bekend.adminpanel.controller;

import com.bek.bek.domain.User;
import com.bek.bek.model.json.request.AuthenticationRequest;
import com.bek.bek.model.json.response.AuthenticationResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.io.IOException;

@RestController
public class AuthenticationController {

    @RequestMapping(
            method = RequestMethod.POST,
            value = "/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest){
        HttpEntity<AuthenticationRequest> request = new HttpEntity<>(authenticationRequest);
        RestTemplate rt = new RestTemplate();
        AuthenticationResponse response = rt.postForObject("http://localhost:9000/api/login",request,AuthenticationResponse.class);
        System.out.println(response.getToken());
        return ResponseEntity.ok(new AuthenticationResponse(response.getToken()));
    }

    @RequestMapping( method = RequestMethod.GET, value = "${route.authentication.refresh}")
    public void authenticationRequestRefresh(HttpServletResponse httpServletResponse,HttpServletRequest request) throws IOException {
    //    return  "redirect:https://localhost:8443/${route.authentication.refresh}";
        // httpServletResponse.setHeader("Location","https://localhost:8443/refresh" );
        RestTemplate rt = new RestTemplate();

        //rt.getForEntity("http://localhost:9000/${route.authentication.refresh}",);

        httpServletResponse.sendRedirect("https://localhost:8443/${route.authentication.refresh}");

    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST)
    public void register(@RequestBody User user) {
//      return "redirect:https://localhost:8443/register" + user;
        RestTemplate rt = new RestTemplate();

        rt.postForLocation("https://localhost:8443/api/register",user);

    }

    @RequestMapping(
            value = "/registerAgent",
            method = RequestMethod.POST)
    public String registerAgent(@RequestBody User user) {
        return "redirect:https://localhost:8443/registerAgent" + user;
    }


    @RequestMapping(
            value = "/activate/{username}",
            method = RequestMethod.GET)
    public void activateUser(@PathVariable("username") String username ){
        HttpServletResponse httpServletResponse = null;
        try {
            httpServletResponse.sendRedirect( "redirect:https://localhost:8443/activate/" + username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}