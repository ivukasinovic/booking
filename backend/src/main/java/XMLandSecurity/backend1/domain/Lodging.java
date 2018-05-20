package XMLandSecurity.backend1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lodgings")
public class Lodging implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "details")
    private String details;

    @Column(name = "image")
    private String image;

    @Column(name = "persons_number", nullable = false)
    private Integer persons_number;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryOfLodging category;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private TypeOfLodging type;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "lodging", cascade = CascadeType.ALL)
    private List<PriceList> priceLists = new ArrayList<PriceList>();


    @OneToMany(mappedBy = "lodging", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy = "lodging", cascade = CascadeType.ALL)
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @ManyToMany(mappedBy = "lodgingList", cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    private List<AdditionalService> additionalServiceList = new ArrayList<AdditionalService>();


    @ManyToMany(mappedBy = "lodgingList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Rating> ratingList = new ArrayList<Rating>();

    public Lodging() {
    }

    public Lodging(String address, String details, String image, Integer persons_number, CategoryOfLodging category, TypeOfLodging type, City city, User user, List<PriceList> priceLists, List<Comment> comments, List<Reservation> reservations, List<AdditionalService> additionalServiceList, List<Rating> ratingList) {
        this.address = address;
        this.details = details;
        this.image = image;
        this.persons_number = persons_number;
        this.category = category;
        this.type = type;
        this.city = city;
        this.user = user;
        this.priceLists = priceLists;
        this.comments = comments;
        this.reservations = reservations;
        this.additionalServiceList = additionalServiceList;
        this.ratingList = ratingList;
    }

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public List<AdditionalService> getAdditionalServiceList() {
        return additionalServiceList;
    }

    public void setAdditionalServiceList(List<AdditionalService> additionalServiceList) {
        this.additionalServiceList = additionalServiceList;
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
}

