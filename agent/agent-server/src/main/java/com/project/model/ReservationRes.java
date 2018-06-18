
package com.project.model;

import javax.persistence.*;


import java.util.Date;

@Entity
@Table
public class ReservationRes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date dateStart;
    private Date dateEnd;
    private boolean active;
    private boolean visited;
    private String user;


    public ReservationRes() {
    }

    public long getId() {
        return id;
    }

    public void setId(long value) {
        this.id = value;
    }


    public Date getDateStart() {
        return dateStart;
    }


    public void setDateStart(Date value) {
        this.dateStart = value;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date value) {
        this.dateEnd = value;
    }

    public boolean isActive() {
        return active;
    }
    public void setActive(boolean value) {
        this.active = value;
    }
    public boolean isVisited() {
        return visited;
    }
    public void setVisited(boolean value) {
        this.visited = value;
    }



}
