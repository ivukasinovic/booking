package com.bek.bek.controller;

import com.bek.bek.domain.AdditionalServiceAdmin;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
// import javax.xml.soap.MimeHeaders;

@RestController
@RequestMapping(value = "/addtional-serviceadmin")
public class AdditionalServiceAdminController {



    @RequestMapping(
            method = RequestMethod.GET
    )
    public void getAll(HttpServletResponse httpServletResponse) {

        httpServletResponse.setHeader("Location","https://localhost:8443/addtional-serviceadmin" );   // "redirect:/https://localhost:8443/addtional-serviceadmin";
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public String getAdditional(@PathVariable("id") Long id) {
        return ("redirect:/https://localhost:8443/addtional-serviceadmin/" + id);
    }

    // ===


    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public String napravi (@RequestBody AdditionalServiceAdmin additionalService) {
        return "redirect:/https://localhost:8443/addtional-serviceadmin" + additionalService;
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT
    )
    public String updateUsers (@PathVariable("id") Long id) {
        return "redirect:/https://localhost:8443/addtional-serviceadmin" + id;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public String izbrisi(@PathVariable("id") Long id){
        return  "redirect:/https://localhost:8443/addtional-serviceadmin" + id;
    }

}
