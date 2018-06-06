package XMLandSecurity.backend1.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="lodging")

public class Lodging implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement(name="id", required=true)
    private Long id;

    @Column(name = "address", nullable = false)
    @XmlElement(name="address", required=true)
    private String address;

    @Column(name = "details")
    @XmlElement(name="details", required=true)
    private String details;

    @Column(name = "image")
    @XmlElement(name="image", required=true)
    private String image;

    @Column(name = "rating", columnDefinition = "Decimal(3,2)")
    @XmlElement(name="rating", required=true)
    private Double rating;

    @Column(name = "persons_number", nullable = false)
    @XmlElement(name="personsNumber", required=true)
    private Integer personsNumber;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "category_id")
    @XmlElement(name="category", required=true)
    private CategoryOfLodging category;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    @XmlElement(name="type", required=true)
    private TypeOfLodging type;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    @XmlElement(name="city", required=true)
    private City city;

    @JsonIgnore
    @OneToMany(mappedBy = "lodging", cascade = CascadeType.ALL)
    @XmlElementWrapper(name="priceLists", required=true)
    @XmlElement(name="priceList", required=true)
    private List<PriceList> priceLists = new ArrayList<PriceList>();

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "agent_id", nullable = false)
    @XmlElement(name="agent", required=true)
    private User agent;

    @JsonIgnore
    @OneToMany(mappedBy = "lodging", cascade = CascadeType.ALL)
    @XmlElementWrapper(name="comments", required=true)
    @XmlElement(name="comment", required=true)
    private List<Comment> comments = new ArrayList<Comment>();

    @JsonIgnore
    @OneToMany(mappedBy = "lodging", cascade = CascadeType.ALL)
    @XmlElementWrapper(name="reservations", required=true)
    @XmlElement(name="reservation", required=true)
    private List<Reservation> reservations = new ArrayList<Reservation>();



    @OneToMany(mappedBy = "lodging", cascade = CascadeType.ALL)
    @XmlElementWrapper(name="ratingList", required=true)
    @XmlElement(name="rating", required=true)
    private List<Rating> ratingList = new ArrayList<Rating>();

    // ==========
    @JsonIgnore
    @ManyToMany(mappedBy = "lodgingList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @XmlElementWrapper(name="additionalServiceList", required=true)
    @XmlElement(name="additionalService", required=true)
    private List<AdditionalService> additionalServiceList = new ArrayList<AdditionalService>();
    // ==========


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
        return personsNumber;
    }

    public void setPersons_number(Integer persons_number) {
        this.personsNumber = persons_number;
    }

//    public List<AdditionalService> getAdditionalServices_list() {
//        return additionalServices_list;
//    }
//
//    public void setAdditionalServices_list(List<AdditionalService> additionalServices_list) {
//        this.additionalServices_list = additionalServices_list;
//    }
}

