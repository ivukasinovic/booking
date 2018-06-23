package XMLandSecurity.backend1.domain;

import java.util.Date;

public class CommentCloud {

    private String body;
    private boolean accepted;
    private Long user;
    private Long lodging;
    private Date dateCreated;
    private Long star;

    public CommentCloud() {
    }


    public CommentCloud(String body, boolean accepted, Long user, Long lodging, Date dateCreated, Long star) {
        this.body = body;
        this.accepted = accepted;
        this.user = user;
        this.lodging = lodging;
        this.dateCreated = dateCreated;
        this.star = star;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getLodging() {
        return lodging;
    }

    public void setLodging(Long lodging) {
        this.lodging = lodging;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Long getStar() {
        return star;
    }

    public void setStar(Long star) {
        this.star = star;
    }
}
