package com.project.controller;

import com.project.converter.Converters;
import com.project.repository.LodgingResRepository;
import com.project.utility.XMLSigningUtility;
import com.project.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.w3c.dom.Document;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.security.Principal;
import java.util.List;


/**
 * Created by Ivan V. on 06-Jun-18
 */
@RestController
@RequestMapping(value = "/lodgings")
public class LodgingController {


    @Autowired
    private Converters converters;

    @Autowired
    private LodgingResRepository lodgingResRepository;

    @RequestMapping(method = RequestMethod.GET,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLodgings(){
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetLodgingsRequest request = new GetLodgingsRequest();
        request.setLodgings("all");
        GetLodgingsResponse response = objPort.getLodgings(request);
        return new ResponseEntity<>(response.getLodgingRes(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
                    value = "/categories",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryOfLodging>> getCategories(){
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetLodgingCategoriesRequest request = new GetLodgingCategoriesRequest();
        request.setTypes("all");
        GetLodgingCategoriesResponse response = objPort.getLodgingCategories(request);
        List<CategoryOfLodging> categories = response.getTypes();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,
            value = "/additions",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AdditionalService>> getAdditions(){
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetAdditionsRequest request = new GetAdditionsRequest();
        request.setTypes("all");
        GetAdditionsResponse response = objPort.getAdditions(request);
        List<AdditionalService> additions = response.getTypes();
        return new ResponseEntity<>(additions, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET,
            value = "/cities",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<City>> getCities(){
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetCitiesRequest request = new GetCitiesRequest();
        request.setCities("all");
        GetCitiesResponse response = objPort.getCities(request);
        List<City> cities = response.getCities();
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET,
            value = "/types",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TypeOfLodging>> getTypes(){

        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetLodgingTypesRequest request = new GetLodgingTypesRequest();
        request.setTypes("all");
        GetLodgingTypesResponse response = objPort.getLodgingTypes(request);
        List<TypeOfLodging> types = response.getTypes();
        return new ResponseEntity<>(types, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> postLodging(@RequestBody SetLodgingRequest request, HttpSession session){
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        System.out.println(session.getAttribute("user"));
//        request.getLodging().setAgent();
        request.getLodging().setAgent(getUsername());
        SetLodgingResponse response = objPort.setLodging(request);
        com.project.model.LodgingRes lodgingRes = converters.convertLodging(request.getLodging());
        lodgingResRepository.save(lodgingRes);



        System.out.println(response);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    String getUsername(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        com.project.model.User user = (com.project.model.User) session.getAttribute("user");
        return user.getUsername();
    }
}