package com.bek.bek.domain;


import java.io.Serializable;


public class Comment implements Serializable {


    private Long id;


    private String body;


    private Boolean accepted;


    private Lodging lodging;


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
