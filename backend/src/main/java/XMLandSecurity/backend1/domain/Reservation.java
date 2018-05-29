package XMLandSecurity.backend1.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="reservation")

public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement(name="id", required=true)
    private Long id;

    @NotNull
    @Column(name = "date_start",nullable = false,columnDefinition="DATETIME")
    @XmlElement(name="dateStart", required=true)
    private Date dateStart;

    @NotNull
    @Column(name = "date_end",nullable = false,columnDefinition="DATETIME")
    @XmlElement(name="dateEnd", required=true)
    private Date dateEnd;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    @XmlElement(name="active", required=true)
    private Boolean active;

    @Column(name = "visited", nullable = false, columnDefinition = "boolean default false")
    @XmlElement(name="visited", required=true)
    private Boolean visited;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "lodging_id", nullable = false)
    @XmlElement(name="lodging", required=true)
    private Lodging lodging;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @XmlElement(name="user", required=true)
    private User user;


    public Reservation() {
    }

    public Reservation(Date dateStart, Date dateEnd, Boolean active, Boolean visited, Lodging lodging, User user) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.active = active;
        this.visited = visited;
        this.lodging = lodging;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    public Lodging getLodging() {
        return lodging;
    }

    public void setLodging(Lodging lodging) {
        this.lodging = lodging;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
