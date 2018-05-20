package XMLandSecurity.backend1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ratings")
public class Rating implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_created", nullable = false)
    private LocalDateTime dateCreated;

    @Column(name = "star", nullable = false)
    private Long star;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "lodgings_ratings",
            joinColumns = {@JoinColumn(name = "rating_id")},
            inverseJoinColumns = {@JoinColumn(name = "lodging_id")}
    )
    private List<Lodging> lodgingList = new ArrayList<Lodging>();

    public Rating() {
    }

    public Rating(LocalDateTime dateCreated, Long star, User user, List<Lodging> lodgingList) {
        this.dateCreated = dateCreated;
        this.star = star;
        this.user = user;
        this.lodgingList = lodgingList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Lodging> getLodgingList() {
        return lodgingList;
    }

    public void setLodgingList(List<Lodging> lodgingList) {
        this.lodgingList = lodgingList;
    }
}
