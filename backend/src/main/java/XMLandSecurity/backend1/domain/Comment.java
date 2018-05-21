package XMLandSecurity.backend1.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "accepted", nullable = false, columnDefinition = "boolean default false")
    private Boolean accepted;

    @ManyToOne
    @JoinColumn(name = "lodging_id", nullable = false)
    private Lodging lodging;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment() {
    }

    public Comment(String body, Boolean accepted, Lodging lodging, User user) {
        this.body = body;
        this.accepted = accepted;
        this.lodging = lodging;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
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
