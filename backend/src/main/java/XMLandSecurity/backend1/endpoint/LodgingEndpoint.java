package XMLandSecurity.backend1.endpoint;

import XMLandSecurity.backend1.domain.*;
import XMLandSecurity.backend1.service.*;
import XMLandSecurity.backend1.utility.Converter;
import XMLandSecurity.backend1.ws.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private ImageService imageService;


    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getLodgingsRequest")
    @ResponsePayload
    public GetLodgingsResponse getLodgingsRequest(@RequestPayload GetLodgingsRequest request) {
        GetLodgingsResponse response = new GetLodgingsResponse();
        if (request.getLodgings().equals("all")) {
            List<Lodging> lodgings = lodgingService.findAll();
            for (Lodging lodging: lodgings) {
                LodgingRes lodgingRes = new LodgingRes();
                lodgingRes.setAddress(lodging.getAddress());
                lodgingRes.setCategory(lodging.getCategory().getId());
                lodgingRes.setTitle(lodging.getTitle());
                lodgingRes.setCity(lodging.getCity().getId());
                lodgingRes.setDetails(lodging.getDetails());
                lodgingRes.setType(lodging.getType().getId());
                lodgingRes.setAgent(lodging.getAgent().getUsername());
                lodgingRes.setPersonsNumber(BigInteger.valueOf(lodging.getPersons_number()));
                for(Image img : lodging.getImages()){
                    lodgingRes.getImagesList().add(img.getUrl());
                }
                for(AdditionalService additionalService: lodging.getAdditionalServiceList()){
                    lodgingRes.getAdditionService().add(additionalService.getId().toString());
                }
                response.getLodgingRes().add(lodgingRes);
            }
        }
        return response;
    }

    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "setLodgingRequest")
    @ResponsePayload
    public SetLodgingResponse setLodgingRequest(@RequestPayload SetLodgingRequest request){
        SetLodgingResponse response = new SetLodgingResponse();

        Lodging lodging = new Lodging();
        lodging.setAgent(userService.findByUsername(request.getLodging().getAgent()));
        lodging.setType(typeOfLodgingService.findOne(request.getLodging().getType()));
        lodging.setCity(cityService.findOne(request.getLodging().getCity()));
        lodging.setCategory(categoryOfLodgingService.findOne(request.getLodging().getCategory()));
        lodging.setTitle(request.getLodging().getTitle());
        lodging.setAddress(request.getLodging().getAddress());
        lodging.setDetails(request.getLodging().getDetails());
        //TODO
        // lodging.setImage(request.getLodging().getImage());
        lodging.setRating(0.0);
        lodging.setPersons_number(request.getLodging().getPersonsNumber().intValue());

        Lodging savedLodging  = lodgingService.save(lodging);
        for(String ads: request.getLodging().getAdditionService()){
            AdditionalService additionalService = additionalServiceService.findOne(Long.valueOf(ads));
            additionalService.getLodgingList().add(savedLodging);
            additionalServiceService.save(additionalService);
        }
        response.setName("success");
        return response;
    }
    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getMessagesRequest")
    @ResponsePayload
    public GetMessagesResponse getMessagesResponse(@RequestPayload GetMessagesRequest request) throws DatatypeConfigurationException {
        GetMessagesResponse response = new GetMessagesResponse();
        String agent = request.getResponse();
        User agentObj = userService.findByUsername(agent);
        List<Message> messages = messageService.findByReceiver_Id(agentObj.getId());
        for (Message msg: messages) {
            MessageRes messageRes = new MessageRes();
            messageRes.setTitle(msg.getTitle());
            messageRes.setBody(msg.getBody());

//            Date daatiDatum = msg.getDateSent();
//            GregorianCalendar c = new GregorianCalendar();
//            c.setTime(daatiDatum);
//            XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

            XMLGregorianCalendar datumDati = Converter.vracaXmlDate(msg.getDateSent());

            messageRes.setDateSent(datumDati);
            messageRes.setId(msg.getId());
            messageRes.setSender(msg.getSender().getUsername());
            messageRes.setReceiver(msg.getReceiver().getUsername());
            response.getMessageRes().add(messageRes);
        }
        return response;
    }
    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "setMessagesRequest")
    @ResponsePayload
    public SetMessagesResponse setMessagesResponse(@RequestPayload SetMessagesRequest request){
        SetMessagesResponse response = new SetMessagesResponse();
        Message message = new Message();
        message.setDateSent(new Date());
        User sender = userService.findByUsername(request.getMessageRes().getReceiver());
        User receiver = userService.findByUsername(request.getMessageRes().getSender());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setBody(request.getMessageRes().getBody());
        message.setTitle(request.getMessageRes().getTitle());
     //   message.setDateSent(request.getMessageRes().getDateSent());
        messageService.save(message);
        response.setMessageRes(request.getMessageRes());

        return  response;
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
    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "getImagesRequest")
    @ResponsePayload
    public GetImagesResponse getImagesRequest(@RequestPayload GetImagesRequest request){
        GetImagesResponse response = new GetImagesResponse();
        List<Image> images = imageService.findAll();
        for(Image im : images){
            im.setLodging(null);
            response.getImagesList().add(im);
        }
        return response;

    }
    @PayloadRoot(namespace = "http://bookingxml.com/soap-example", localPart = "setOccupancyRequest")
    @ResponsePayload
    public SetOccupancyResponse setOccupancyRequest(@RequestPayload SetOccupancyRequest request){
        SetOccupancyResponse response = new SetOccupancyResponse();
        Reservation reservation = new Reservation();
        User agent = userService.findByUsername(request.getAgent());
        reservation.setUser(agent);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String dateStart = request.getStart() + " 00:00:00";
        String dateEnd = request.getEnd() + " 00:00:00";
        try {
            reservation.setDateStart(format.parse(dateStart));
            reservation.setDateEnd(format.parse(dateEnd));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        reservation.setVisited(false);
        reservation.setActive(true);
        reservation.setLodging(lodgingService.findOne(request.getLodging()));
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
                lodging.setTitle(res.getLodging().getTitle());
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
