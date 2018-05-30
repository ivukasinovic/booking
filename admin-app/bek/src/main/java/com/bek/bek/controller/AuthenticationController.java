package com.bek.bek.controller;//package bekend.adminpanel.controller;

import com.bek.bek.domain.User;
import com.bek.bek.model.json.request.AuthenticationRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class AuthenticationController {

    @RequestMapping(method = RequestMethod.POST,value = "/login")
    public void authenticationRequest(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse httpServletResponse) throws IOException {
        //return  "redirect:/https://localhost:8443/${route.authentication}";
//        httpServletResponse.setHeader("parameter", authenticationRequest);
//        httpServletResponse.setHeader("Location","https://localhost:8443/${route.authentication}" );
         RestTemplate rt = new RestTemplate();

         rt.postForLocation("https://localhost:8443/api/login",authenticationRequest);

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