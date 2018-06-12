package XMLandSecurity.backend1.endpoint;

import XMLandSecurity.backend1.domain.*;
import XMLandSecurity.backend1.service.*;
import XMLandSecurity.backend1.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

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

    @Autowired
    private TypeOfLodgingService typeOfLodgingService;

    @Autowired
    private CityService cityService;

    @Autowired
    private LodgingService lodgingService;

    @Autowired
    private CategoryOfLodgingService categoryOfLodgingService;

    @Autowired
    private AdditionalServiceService additionalServiceService;

    @Autowired
    private MessageService messageService;

    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "setLodgingRequest")
    @ResponsePayload
    public SetLodgingResponse setLodgingRequest(@RequestPayload SetLodgingRequest request){
        SetLodgingResponse response = new SetLodgingResponse();

        Lodging lodging = new Lodging();
        lodging.setAgent(userService.findOne(request.getAgent()));
        lodging.setType(typeOfLodgingService.findOne(request.getType()));
        lodging.setCity(cityService.findOne(request.getCity()));
        lodging.setCategory(categoryOfLodgingService.findOne(request.getCategory()));
        lodging.setTitle(request.getTitle());
        lodging.setAddress(request.getAddress());
        lodging.setDetails(request.getDetails());
        lodging.setImage(request.getImage());
        lodging.setPersons_number(request.getPersonsNumber().intValue());

        Lodging savedLodging  = lodgingService.save(lodging);
        for(String ads: request.getAdditionService()){
            AdditionalService additionalService = additionalServiceService.findOne(Long.valueOf(ads));
            additionalService.getLodgingList().add(savedLodging);
            additionalServiceService.save(additionalService);
        }
        response.setName("vratio");
        return response;
    }
    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getMessagesRequest")
    @ResponsePayload
    public GetMessagesResponse getMessagesResponse(@RequestPayload GetMessagesRequest request){
        GetMessagesResponse response = new GetMessagesResponse();
        //Hardkodovan agent (2)
        List<Message> messages = messageService.findByReceiver_Id(4L);
        for (Message msg: messages) {
            MessageRes messageRes = new MessageRes();
            messageRes.setTitle(msg.getTitle());
            messageRes.setBody(msg.getBody());
            messageRes.setId(msg.getId());
            messageRes.setSender(msg.getSender().getUsername());
            messageRes.setReceiver(msg.getReceiver().getUsername());
            response.getMessageRes().add(messageRes);
        }
        return response;
    }

    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "setCompletedLodgingRequest")
    @ResponsePayload
    public SetCompletedLodgingResponse setCompletedLodgingRequest(@RequestPayload SetCompletedLodgingRequest request){
        SetCompletedLodgingResponse response = new SetCompletedLodgingResponse();
        Reservation reservation = reservationService.findOne(request.getReservation());
        reservation.setActive(false);
        reservation.setVisited(true);
        Reservation res = reservationService.save(reservation);
        System.out.println(res.getDateEnd());
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
    public GetReservationsResponse getReservationsRequest(@RequestPayload GetReservationsRequest request){
        GetReservationsResponse response = new GetReservationsResponse();
        if(request.getType().equals("all")){
            List<Reservation> reservations = reservationService.findAll();
            for (Reservation res: reservations) {
                User user = new User();
                user.setUsername(res.getUser().getUsername());
                res.setUser(user);
                Lodging lodging = new Lodging();
                lodging.setId(res.getLodging().getId());
                res.setLodging(lodging);
              //  res.setUser(null);
              //  res.setLodging(null);
                response.getReservations().add(res);
            }
        }
        return response;
    }

    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getLodgingCategoriesRequest")
    @ResponsePayload
    public GetLodgingCategoriesResponse getLodgingCategoriesRequest(@RequestPayload GetLodgingCategoriesRequest request) {
        GetLodgingCategoriesResponse response = new GetLodgingCategoriesResponse();
        List<CategoryOfLodging> categories = categoryOfLodgingService.findAll();
        for (CategoryOfLodging cat: categories) {
            response.getTypes().add(cat);
        }
        return response;
    }
    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getAdditionsRequest")
    @ResponsePayload
    public GetAdditionsResponse getAdditionsRequest(@RequestPayload GetAdditionsRequest getAdditionsRequest){
        GetAdditionsResponse response = new GetAdditionsResponse();
        List<AdditionalService> additionalServices = additionalServiceService.findAll();
        for(AdditionalService s : additionalServices){
            s.setLodgingList(null);
            response.getTypes().add(s);
        }
        return response;
    }

    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getCitiesRequest")
    @ResponsePayload
    public GetCitiesResponse getCtitiesRequest(@RequestPayload GetCitiesRequest getCitiesRequest){
        GetCitiesResponse response = new GetCitiesResponse();
        List<City> cities = cityService.findAll();
        for(City city: cities){
            city.setCountry(null);
            response.getCities().add(city);
        }
        return response;
    }
    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getLodgingTypesRequest")
    @ResponsePayload
    public GetLodgingTypesResponse getLodgingTypesResponse(@RequestPayload GetLodgingTypesRequest getLodgingTypesRequest){
        GetLodgingTypesResponse response = new GetLodgingTypesResponse();
        List<TypeOfLodging> types = typeOfLodgingService.findAll();
        for(TypeOfLodging type: types){
            response.getTypes().add(type);
        }
        return response;
    }
}
