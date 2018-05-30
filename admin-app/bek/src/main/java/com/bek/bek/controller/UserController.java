package com.bek.bek.controller;//package bekend.adminpanel.controller;

import com.bek.bek.domain.User;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public String getAllUsers() {
        return "redirect:/https://localhost:8443/users";
    }

    @RequestMapping(
            value = "/samo",
            method = RequestMethod.GET
    )
    public String getAllUsuallyUsers() {
    return "redirect:/https://localhost:8443/users/samo";
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
    public String izbrisi(@PathVariable("id") Long id){
        return  "https://localhost:8443/users/" + id;

    }

    @RequestMapping(
            value = "activate/{id}",
            method = RequestMethod.GET
    )
    public String activate(@PathVariable("id") Long id) {
        return  "https://localhost:8443/users/activate/" + id;
    }

    @RequestMapping(
            value = "deactive/{id}",
            method = RequestMethod.GET
    )
    public String deactivate(@PathVariable("id") Long id) {
        return  "https://localhost:8443/users/deactive/" + id;
    }


}
