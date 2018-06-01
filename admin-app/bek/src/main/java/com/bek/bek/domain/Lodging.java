package com.bek.bek.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Lodging implements Serializable {


    private Long id;


    private String address;


    private String details;


    private String image;


    private Double rating;


    private Integer persons_number;


    private CategoryOfLodging category;


    private TypeOfLodging type;


    private City city;


    private List<PriceList> priceLists = new ArrayList<PriceList>();


    private User agent;


    private List<Comment> comments = new ArrayList<Comment>();


    private List<Reservation> reservations = new ArrayList<Reservation>();

//    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//    @JsonIdentityReference(alwaysAsId = true)
//    @ManyToOne
//    @JoinColumn(name = "additional_service_id", nullable = false)
//    private AdditionalService additionalService;


    private List<Rating> ratingList = new ArrayList<Rating>();

    // ==========
 //   @JsonIgnore
//        @ManyToMany(mappedBy = "lodgingList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//        @XmlElementWrapper(name="additionalServiceList", required=true)
//        @XmlElement(name="additionalService", required=true)
//        private List<AdditionalService> additionalServiceList = new ArrayList<AdditionalService>();
    // ==========


    private List<AdditionalService> additionalServices_list= new ArrayList<AdditionalService>();


    public Lodging() {
    }


    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public User getAgent() {
        return agent;
    }

    public void setAgent(User agent) {
        this.agent = agent;
    }

//    public AdditionalService getAdditionalService() {
//        return additionalService;
//    }
//
//    public void setAdditionalService(AdditionalService additionalService) {
//        this.additionalService = additionalService;
//    }

    public CategoryOfLodging getCategory() {
        return category;
    }

    public void setCategory(CategoryOfLodging category) {
        this.category = category;
    }

    public TypeOfLodging getType() {
        return type;
    }

    public void setType(TypeOfLodging type) {
        this.type = type;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public List<PriceList> getPriceLists() {
        return priceLists;
    }

    public void setPriceLists(List<PriceList> priceLists) {
        this.priceLists = priceLists;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getPersons_number() {
        return persons_number;
    }

    public void setPersons_number(Integer persons_number) {
        this.persons_number = persons_number;
    }

    public List<AdditionalService> getAdditionalServices_list() {
        return additionalServices_list;
    }

    public void setAdditionalServices_list(List<AdditionalService> additionalServices_list) {
        this.additionalServices_list = additionalServices_list;
    }
}

