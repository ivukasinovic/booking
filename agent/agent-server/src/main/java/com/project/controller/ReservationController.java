package com.project.controller;

import com.project.ws.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ivan V. on 05-Jun-18
 */
@RequestMapping(value = "/reservations")
@RestController
public class ReservationController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reservation>> getReservations(){
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetReservationsRequest request = new GetReservationsRequest();
        request.setType("all");
        GetReservationsResponse response = objPort.getReservations(request);
        List<Reservation> reservations = response.getReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
    @RequestMapping(value = "/completed/{id}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> setCompleted(@PathVariable Long id){
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        SetCompletedLodgingRequest request = new SetCompletedLodgingRequest();
        request.setReservation(id.toString());
        SetCompletedLodgingResponse response = objPort.setCompletedLodging(request);
        String resp = response.getStatus();
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @RequestMapping(value = "/messages",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MessageRes>> getMessages(){
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetMessagesRequest request = new GetMessagesRequest();
        request.setResponse("all");
        GetMessagesResponse response = objPort.getMessages(request);
        List<MessageRes> messageRes = response.getMessageRes();
        return new ResponseEntity<>(messageRes, HttpStatus.OK);
    }
}
