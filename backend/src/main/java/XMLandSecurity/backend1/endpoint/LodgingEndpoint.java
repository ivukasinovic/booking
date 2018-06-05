package XMLandSecurity.backend1.endpoint;

import XMLandSecurity.backend1.domain.Reservation;
import XMLandSecurity.backend1.domain.User;
import XMLandSecurity.backend1.service.ReservationService;
import XMLandSecurity.backend1.service.UserService;
import XMLandSecurity.backend1.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan V. on 04-Jun-18
 */
@Endpoint
public class LodgingEndpoint {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UserService userService;

//    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "setLodgingRequest")
//    @ResponsePayload
//    public SetLodgingResponse setLodgingRequest(@RequestPayload Lodging lodging){
//        SetLodgingResponse response = new SetLodgingResponse();
//        System.out.println(lodging);
//        response.setName("vratio");
//        return response;
//    }

    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "setCompletedLodgingRequest")
    @ResponsePayload
    public SetCompletedLodgingResponse setCompletedLodgingRequest(@RequestPayload SetCompletedLodgingRequest request){
        SetCompletedLodgingResponse response = new SetCompletedLodgingResponse();
        Reservation reservation = reservationService.findOne(request.getReservation());
        reservation.setVisited(true);
        reservationService.save(reservation);
        System.out.println(request.getReservation());
        response.setStatus("success");
        return response;
    }
    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "setOccupancyRequest")
    @ResponsePayload
    public SetOccupancyResponse setOccupancyRequest(@RequestPayload SetOccupancyRequest request){
        SetOccupancyResponse response = new SetOccupancyResponse();
        Reservation reservation = new Reservation();
        //HARDKOD
        User agent = userService.findOne(2L);
        reservation.setUser(agent);
        reservation.setDateStart(request.getStart().toGregorianCalendar().getTime());
        reservation.setDateEnd(request.getEnd().toGregorianCalendar().getTime());
        reservation.setVisited(false);
        reservation.setActive(true);
        reservationService.save(reservation);
        response.setSuccess("success");
        return response;
    }

    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getReservationsRequest")
    @ResponsePayload
    public GetReservationsResponse getReservationsRequest(){
        GetReservationsResponse response = new GetReservationsResponse();
        List<Reservation> reservations = reservationService.findAll();
        for (Reservation res: reservations) {
            res.setUser(null);
            res.setLodging(null);
            response.getReservation().add(res);
        }
        return response;
    }
}
