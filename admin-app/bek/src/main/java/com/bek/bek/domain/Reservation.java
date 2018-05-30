package com.bek.bek.domain;


import java.io.Serializable;
import java.util.Date;


public class Reservation implements Serializable {


    private Long id;

    private Date dateStart;


    private Date dateEnd;


    private Boolean active;


    private Boolean visited;


    private Lodging lodging;


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
