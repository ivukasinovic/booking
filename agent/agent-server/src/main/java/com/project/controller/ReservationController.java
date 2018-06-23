package com.project.controller;

import com.project.converter.Converters;
import com.project.repository.MessageResRepository;
import com.project.repository.ReservationResRepository;
import com.project.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Ivan V. on 05-Jun-18
 */
@RequestMapping(value = "/reservations")
@RestController
public class ReservationController {

    @Autowired
    private Converters converters;

    @Autowired
    private MessageResRepository messageResRepository;

    @Autowired
    private ReservationResRepository reservationResRepository;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Reservation>> getReservations(){
        try{
            getUsername();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetReservationsRequest request = new GetReservationsRequest();
        request.setType("all");
        GetReservationsResponse response = objPort.getReservations(request);
        List<Reservation> reservations = response.getReservations();
        return new ResponseEntity<>(reservations, HttpStatus.OK);
    }

    @RequestMapping(value = "/occupancy",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> setOccupancy(@RequestParam("id") Long id, @RequestParam("dateStart") String dateStart, @RequestParam("dateEnd") String dateEnd){
        String agent;
        try{
            agent = getUsername();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        SetOccupancyRequest request = new SetOccupancyRequest();
        request.setLodging(id);
        request.setStart(dateStart);
        request.setEnd(dateEnd);
        request.setAgent(agent);
        SetOccupancyResponse response = objPort.setOccupancy(request);

        com.project.model.ReservationRes lodgingRes = converters.updateLodging(id,dateStart,dateEnd);
        reservationResRepository.save(lodgingRes);

        return new ResponseEntity<>(HttpStatus.OK);

    }
    @RequestMapping(value = "/completed/{id}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> setCompleted(@PathVariable Long id){
        try{
            getUsername();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
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
        String agent;
        try{
            agent = getUsername();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        GetMessagesRequest request = new GetMessagesRequest();
        request.setResponse(agent);
        GetMessagesResponse response = objPort.getMessages(request);
        List<MessageRes> messageRes = response.getMessageRes();
        return new ResponseEntity<>(messageRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/reply",
                    produces = MediaType.APPLICATION_JSON_VALUE,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MessageRes> reply(@RequestBody MessageRes messageRes){
        try{
            getUsername();
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        LodgingService objMethod = new LodgingService();
        LodgingServicePort objPort = objMethod.getLodgingServicePortSoap11();
        SetMessagesRequest request = new SetMessagesRequest();
        request.setMessageRes(messageRes);
        SetMessagesResponse response = objPort.setMessages(request);
        MessageRes msg = response.getMessageRes();

        com.project.model.MessageRes  messageRes1 = converters.convertMessage(messageRes);
        messageResRepository.save(messageRes1);

        return new ResponseEntity<>(msg, HttpStatus.CREATED);
    }

    String getUsername(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session= attr.getRequest().getSession(true);
        com.project.model.User user = (com.project.model.User) session.getAttribute("user");
        return user.getUsername();
    }
}
