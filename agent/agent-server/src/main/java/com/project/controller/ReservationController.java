package com.project.controller;

import com.project.model.Reservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ivan V. on 05-Jun-18
 */
@RequestMapping(value = "/reservations")
@RestController
public class ReservationController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reservation>> getReservations(){
        List<Reservation> reservations = null;
        System.out.println("LOLAAA");
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }
}
