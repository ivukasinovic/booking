package com.bek.bek.controller;

import com.bek.bek.domain.AdditionalService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping(value = "/addtional-serviceadmin")
public class AdditionalServiceAdminController {


    private final String port = "https://localhost:8443";

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity< Object[]> getAll() {
        RestTemplate rt = new RestTemplate();
        ResponseEntity<Object[]> responseEntity = rt.getForEntity(port + "/api/addtional-service", Object[].class);
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
        ResponseEntity<Object> responseEntity = rt.getForEntity(port + "/api/addtional-service/"+id, Object.class,id);
        Object object = responseEntity.getBody();


        return new ResponseEntity<>(responseEntity, HttpStatus.OK);
    }

    // ===
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<AdditionalService>  napravi (@RequestBody AdditionalService additionalService) {
        HttpEntity<AdditionalService> request = new HttpEntity<>(additionalService);
        RestTemplate rt = new RestTemplate();
        AdditionalService response = rt.postForObject(port + "/api/addtional-service", request, AdditionalService.class);

        return ResponseEntity.ok(new AdditionalService());
    }


    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.DELETE
    )
    public void izbrisi(@PathVariable("id") Long id){
       // return  "redirect:/https://localhost:8443/addtional-serviceadmin" + id;
        RestTemplate rt = new RestTemplate();
        rt.delete(port + "/api/addtional-service/"+id);
    }

}
