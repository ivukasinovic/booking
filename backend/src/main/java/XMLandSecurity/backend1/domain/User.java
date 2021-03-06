package XMLandSecurity.backend1.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.intellij.lang.annotations.RegExp;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Ivan V. on 07-May-18
 */
@Entity

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")

public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @XmlElement(name = "id", required = true)
    private Long id;

    @NotEmpty
    @Size(min = 3, max = 30)
    @Column(nullable = false, unique = true)
    @XmlElement(name = "username", required = true)
    private String username;

    @NotEmpty
    @Size(min = 4, max = 101)     // kad je bilo 30 bacalo zbog hesha greske
    @Column(nullable = false)
    @XmlElement(name = "passwordHash", required = true)
    private String passwordHash;

    @NotEmpty
    @Size(min = 3, max = 30)
    @Column(nullable = false)
    @XmlElement(name = "name", required = true)
    private String name;

    @NotEmpty
    @Size(min = 3, max = 30)
    @Column(nullable = false)
    @XmlElement(name = "surname", required = true)
    private String surname;

    @NotEmpty
    @Size(min = 3, max = 30)
    @Email
    @Column(nullable = false, unique = true)
    // Moze biti nula zbog agenta kad se registruje ne unosi email (ali smo mi dodali)
    @XmlElement(name = "email", required = true)
    private String email;

    @Column(nullable = false)
    @XmlElement(name = "city", required = true)
    private String city;

    @Column(nullable = true)  // Posto nema za obicne korisnike
    @XmlElement(name = "adress", required = false)
    private String adress;


    @RegExp(prefix = "[0-9]+")
    @Size(min = 6, max = 15)
    @NotEmpty(message = "min=6 , a max 15.")
    @Column(nullable = false)
    @XmlElement(name = "number", required = true)
    private String number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @XmlElement(name = "role", required = true)
    private Role role;

    @Column(nullable = true)
    @XmlElement(name = "lastPasswordReset", required = false)
    private Date lastPasswordReset;

    @Column(nullable = false)
    @XmlElement(name = "activated", required = true)
    private boolean activated;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "receviedMessages", required = false)
    @XmlElement(name = "message", required = false)
    private List<Message> receviedMessages = new ArrayList<Message>();

    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "sentMessages", required = false)
    @XmlElement(name = "message", required = false)
    private List<Message> sentMessages = new ArrayList<Message>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "reservations", required = false)
    @XmlElement(name = "reservation", required = false)
    private List<Reservation> reservations = new ArrayList<Reservation>();

    @JsonIgnore
    @XmlElementWrapper(name = "lodgings", required = false)
    @XmlElement(name = "lodging", required = false)
    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<Lodging> lodgings = new ArrayList<Lodging>();

    @JsonIgnore
    @XmlElementWrapper(name = "ratings", required = false)
    @XmlElement(name = "rating", required = false)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Rating> ratings = new ArrayList<Rating>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "comments", required = true)
    @XmlElement(name = "comment", required = true)
    private List<Comment> comments = new ArrayList<Comment>();


    public User() {
    }

    public List<Message> getReceviedMessages() {
        return receviedMessages;
    }

    public void setReceviedMessages(List<Message> receviedMessages) {
        this.receviedMessages = receviedMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Lodging> getLodgings() {
        return lodgings;
    }

    public void setLodgings(List<Lodging> lodgings) {
        this.lodgings = lodgings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
