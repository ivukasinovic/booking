package XMLandSecurity.backend1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table
public class Reservation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date_start",nullable = false,columnDefinition="DATETIME")
    private LocalDateTime dateStart;

    @Column(name = "date_end",nullable = false,columnDefinition="DATETIME")
    private LocalDateTime dateEnd;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    private Boolean active;

    @Column(name = "visited", nullable = false, columnDefinition = "boolean default false")
    private Boolean visited;

    @ManyToOne
    @JoinColumn(name = "lodging_id", nullable = false)
    private Lodging lodging;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    public Reservation() {
    }

    public Reservation(LocalDateTime dateStart, LocalDateTime dateEnd, Boolean active, Boolean visited, Lodging lodging, User user) {
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

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
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
