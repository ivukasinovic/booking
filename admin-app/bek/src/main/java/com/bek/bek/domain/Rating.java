package com.bek.bek.domain;


import java.io.Serializable;
import java.util.Date;


public class Rating implements Serializable {


    private Long id;


    private Date dateCreated;


    private Long star;


    private User user;


    private Lodging lodging;

    public Rating() {
    }

    public Rating(Date dateCreated, Long star, User user, Lodging lodging) {
        this.dateCreated = dateCreated;
        this.star = star;
        this.user = user;
        this.lodging = lodging;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lodging getLodging() {
        return lodging;
    }

    public void setLodging(Lodging lodging) {
        this.lodging = lodging;
    }
}
