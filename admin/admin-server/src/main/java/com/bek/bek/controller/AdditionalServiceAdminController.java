package com.bek.bek.controller;

import com.bek.bek.domain.AdditionalServiceAdmin;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping(value = "/addtional-serviceadmin")
public class AdditionalServiceAdminController {


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity< Object[]> getAll() {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object[]> responseEntity = rt.getForEntity("http://localhost:8080/api/addtional-serviceadmin", Object[].class);
        Object[] objects = responseEntity.getBody();
        return new ResponseEntity<>(objects,HttpStatus.OK);
 }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET
    )
    public ResponseEntity<?> getAdditional(@PathVariable("id") Long id) {
       //  return ("redirect:/https://localhost:8080/addtional-serviceadmin/" + id);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object> responseEntity = rt.getForEntity("http://localhost:8080/api/addtional-serviceadmin/"+id, Object.class,id);
        Object object = responseEntity.getBody();


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    // ===
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalServiceAdmin>  napravi (@RequestBody AdditionalServiceAdmin additionalService) {
        HttpEntity<AdditionalServiceAdmin> request = new HttpEntity<>(additionalService);
        RestTemplate rt = new RestTemplate();
        AdditionalServiceAdmin response = rt.postForObject("http://localhost:8080/api/addtional-serviceadmin", request, AdditionalServiceAdmin.class);

        return ResponseEntity.ok(new AdditionalServiceAdmin());
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void izbrisi(@PathVariable("id") Long id){
       // return  "redirect:/https://localhost:8443/addtional-serviceadmin" + id;
        RestTemplate rt = new RestTemplate();
        rt.delete("http://localhost:8080/api/addtional-serviceadmin/"+id);
    }

}
