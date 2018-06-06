
package com.project.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.bookingxml.soap_example package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _City_QNAME = new QName("http://bookingxml.com/soap-example", "city");
    private final static QName _TypeOfLodging_QNAME = new QName("http://bookingxml.com/soap-example", "typeOfLodging");
    private final static QName _Country_QNAME = new QName("http://bookingxml.com/soap-example", "country");
    private final static QName _Lodging_QNAME = new QName("http://bookingxml.com/soap-example", "lodging");
    private final static QName _PriceList_QNAME = new QName("http://bookingxml.com/soap-example", "priceList");
    private final static QName _AdditionalService_QNAME = new QName("http://bookingxml.com/soap-example", "additionalService");
    private final static QName _Message_QNAME = new QName("http://bookingxml.com/soap-example", "message");
    private final static QName _Rating_QNAME = new QName("http://bookingxml.com/soap-example", "rating");
    private final static QName _AdditionalServiceAdmin_QNAME = new QName("http://bookingxml.com/soap-example", "additionalServiceAdmin");
    private final static QName _CategoryOfLodging_QNAME = new QName("http://bookingxml.com/soap-example", "categoryOfLodging");
    private final static QName _User_QNAME = new QName("http://bookingxml.com/soap-example", "user");
    private final static QName _Comment_QNAME = new QName("http://bookingxml.com/soap-example", "comment");
    private final static QName _Reservation_QNAME = new QName("http://bookingxml.com/soap-example", "reservation");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.bookingxml.soap_example
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Lodging }
     * 
     */
    public Lodging createLodging() {
        return new Lodging();
    }

    /**
     * Create an instance of {@link AdditionalService }
     * 
     */
    public AdditionalService createAdditionalService() {
        return new AdditionalService();
    }

    /**
     * Create an instance of {@link Country }
     * 
     */
    public Country createCountry() {
        return new Country();
    }

    /**
     * Create an instance of {@link TypeOfLodging }
     * 
     */
    public TypeOfLodging createTypeOfLodging() {
        return new TypeOfLodging();
    }

    /**
     * Create an instance of {@link GetLodgingCategoriesResponse }
     * 
     */
    public GetLodgingCategoriesResponse createGetLodgingCategoriesResponse() {
        return new GetLodgingCategoriesResponse();
    }

    /**
     * Create an instance of {@link CategoryOfLodging }
     * 
     */
    public CategoryOfLodging createCategoryOfLodging() {
        return new CategoryOfLodging();
    }

    /**
     * Create an instance of {@link City }
     * 
     */
    public City createCity() {
        return new City();
    }

    /**
     * Create an instance of {@link GetCitiesRequest }
     * 
     */
    public GetCitiesRequest createGetCitiesRequest() {
        return new GetCitiesRequest();
    }

    /**
     * Create an instance of {@link Rating }
     * 
     */
    public Rating createRating() {
        return new Rating();
    }

    /**
     * Create an instance of {@link GetLodgingTypesRequest }
     * 
     */
    public GetLodgingTypesRequest createGetLodgingTypesRequest() {
        return new GetLodgingTypesRequest();
    }

    /**
     * Create an instance of {@link GetCitiesResponse }
     * 
     */
    public GetCitiesResponse createGetCitiesResponse() {
        return new GetCitiesResponse();
    }

    /**
     * Create an instance of {@link PriceList }
     * 
     */
    public PriceList createPriceList() {
        return new PriceList();
    }

    /**
     * Create an instance of {@link SetCompletedLodgingRequest }
     * 
     */
    public SetCompletedLodgingRequest createSetCompletedLodgingRequest() {
        return new SetCompletedLodgingRequest();
    }

    /**
     * Create an instance of {@link AdditionalServiceAdmin }
     * 
     */
    public AdditionalServiceAdmin createAdditionalServiceAdmin() {
        return new AdditionalServiceAdmin();
    }

    /**
     * Create an instance of {@link Reservation }
     * 
     */
    public Reservation createReservation() {
        return new Reservation();
    }

    /**
     * Create an instance of {@link GetLodgingCategoriesRequest }
     * 
     */
    public GetLodgingCategoriesRequest createGetLodgingCategoriesRequest() {
        return new GetLodgingCategoriesRequest();
    }

    /**
     * Create an instance of {@link GetAdditionsResponse }
     * 
     */
    public GetAdditionsResponse createGetAdditionsResponse() {
        return new GetAdditionsResponse();
    }

    /**
     * Create an instance of {@link GetAdditionsRequest }
     * 
     */
    public GetAdditionsRequest createGetAdditionsRequest() {
        return new GetAdditionsRequest();
    }

    /**
     * Create an instance of {@link SetCompletedLodgingResponse }
     * 
     */
    public SetCompletedLodgingResponse createSetCompletedLodgingResponse() {
        return new SetCompletedLodgingResponse();
    }

    /**
     * Create an instance of {@link SetOccupancyResponse }
     * 
     */
    public SetOccupancyResponse createSetOccupancyResponse() {
        return new SetOccupancyResponse();
    }

    /**
     * Create an instance of {@link SetLodgingRequest }
     * 
     */
    public SetLodgingRequest createSetLodgingRequest() {
        return new SetLodgingRequest();
    }

    /**
     * Create an instance of {@link SetLodgingResponse }
     * 
     */
    public SetLodgingResponse createSetLodgingResponse() {
        return new SetLodgingResponse();
    }

    /**
     * Create an instance of {@link Message }
     * 
     */
    public Message createMessage() {
        return new Message();
    }

    /**
     * Create an instance of {@link SetOccupancyRequest }
     * 
     */
    public SetOccupancyRequest createSetOccupancyRequest() {
        return new SetOccupancyRequest();
    }

    /**
     * Create an instance of {@link GetReservationsResponse }
     * 
     */
    public GetReservationsResponse createGetReservationsResponse() {
        return new GetReservationsResponse();
    }

    /**
     * Create an instance of {@link Comment }
     * 
     */
    public Comment createComment() {
        return new Comment();
    }

    /**
     * Create an instance of {@link GetReservationsRequest }
     * 
     */
    public GetReservationsRequest createGetReservationsRequest() {
        return new GetReservationsRequest();
    }

    /**
     * Create an instance of {@link GetLodgingTypesResponse }
     * 
     */
    public GetLodgingTypesResponse createGetLodgingTypesResponse() {
        return new GetLodgingTypesResponse();
    }

    /**
     * Create an instance of {@link User.ReceviedMessages }
     * 
     */
    public User.ReceviedMessages createUserReceviedMessages() {
        return new User.ReceviedMessages();
    }

    /**
     * Create an instance of {@link User.SentMessages }
     * 
     */
    public User.SentMessages createUserSentMessages() {
        return new User.SentMessages();
    }

    /**
     * Create an instance of {@link User.Reservations }
     * 
     */
    public User.Reservations createUserReservations() {
        return new User.Reservations();
    }

    /**
     * Create an instance of {@link User.Lodgings }
     * 
     */
    public User.Lodgings createUserLodgings() {
        return new User.Lodgings();
    }

    /**
     * Create an instance of {@link User.Ratings }
     * 
     */
    public User.Ratings createUserRatings() {
        return new User.Ratings();
    }

    /**
     * Create an instance of {@link User.Comments }
     * 
     */
    public User.Comments createUserComments() {
        return new User.Comments();
    }

    /**
     * Create an instance of {@link Lodging.PriceLists }
     * 
     */
    public Lodging.PriceLists createLodgingPriceLists() {
        return new Lodging.PriceLists();
    }

    /**
     * Create an instance of {@link Lodging.Comments }
     * 
     */
    public Lodging.Comments createLodgingComments() {
        return new Lodging.Comments();
    }

    /**
     * Create an instance of {@link Lodging.Reservations }
     * 
     */
    public Lodging.Reservations createLodgingReservations() {
        return new Lodging.Reservations();
    }

    /**
     * Create an instance of {@link Lodging.RatingList }
     * 
     */
    public Lodging.RatingList createLodgingRatingList() {
        return new Lodging.RatingList();
    }

    /**
     * Create an instance of {@link Lodging.AdditionalServicesList }
     * 
     */
    public Lodging.AdditionalServicesList createLodgingAdditionalServicesList() {
        return new Lodging.AdditionalServicesList();
    }

    /**
     * Create an instance of {@link AdditionalService.AdditionalServiceList }
     * 
     */
    public AdditionalService.AdditionalServiceList createAdditionalServiceAdditionalServiceList() {
        return new AdditionalService.AdditionalServiceList();
    }

    /**
     * Create an instance of {@link Country.CityList }
     * 
     */
    public Country.CityList createCountryCityList() {
        return new Country.CityList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link City }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "city")
    public JAXBElement<City> createCity(City value) {
        return new JAXBElement<City>(_City_QNAME, City.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TypeOfLodging }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "typeOfLodging")
    public JAXBElement<TypeOfLodging> createTypeOfLodging(TypeOfLodging value) {
        return new JAXBElement<TypeOfLodging>(_TypeOfLodging_QNAME, TypeOfLodging.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Country }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "country")
    public JAXBElement<Country> createCountry(Country value) {
        return new JAXBElement<Country>(_Country_QNAME, Country.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Lodging }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "lodging")
    public JAXBElement<Lodging> createLodging(Lodging value) {
        return new JAXBElement<Lodging>(_Lodging_QNAME, Lodging.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PriceList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "priceList")
    public JAXBElement<PriceList> createPriceList(PriceList value) {
        return new JAXBElement<PriceList>(_PriceList_QNAME, PriceList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionalService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "additionalService")
    public JAXBElement<AdditionalService> createAdditionalService(AdditionalService value) {
        return new JAXBElement<AdditionalService>(_AdditionalService_QNAME, AdditionalService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Message }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "message")
    public JAXBElement<Message> createMessage(Message value) {
        return new JAXBElement<Message>(_Message_QNAME, Message.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Rating }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "rating")
    public JAXBElement<Rating> createRating(Rating value) {
        return new JAXBElement<Rating>(_Rating_QNAME, Rating.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionalServiceAdmin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "additionalServiceAdmin")
    public JAXBElement<AdditionalServiceAdmin> createAdditionalServiceAdmin(AdditionalServiceAdmin value) {
        return new JAXBElement<AdditionalServiceAdmin>(_AdditionalServiceAdmin_QNAME, AdditionalServiceAdmin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CategoryOfLodging }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "categoryOfLodging")
    public JAXBElement<CategoryOfLodging> createCategoryOfLodging(CategoryOfLodging value) {
        return new JAXBElement<CategoryOfLodging>(_CategoryOfLodging_QNAME, CategoryOfLodging.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link User }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "user")
    public JAXBElement<User> createUser(User value) {
        return new JAXBElement<User>(_User_QNAME, User.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Comment }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "comment")
    public JAXBElement<Comment> createComment(Comment value) {
        return new JAXBElement<Comment>(_Comment_QNAME, Comment.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Reservation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bookingxml.com/soap-example", name = "reservation")
    public JAXBElement<Reservation> createReservation(Reservation value) {
        return new JAXBElement<Reservation>(_Reservation_QNAME, Reservation.class, null, value);
    }

}
