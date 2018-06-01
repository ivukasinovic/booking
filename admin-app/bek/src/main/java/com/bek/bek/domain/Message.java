package com.bek.bek.domain;


import java.io.Serializable;
import java.util.Date;


public class Message implements Serializable {


    private Long id;


    private String title;


    private String body;


    private Date dateSent;


    private User receiver;


    private User sender;

    public Message() {
    }

    public Message(String title, String body, Date dateSent, User receiver, User sender) {
        this.title = title;
        this.body = body;
        this.dateSent = dateSent;
        this.receiver = receiver;
        this.sender = sender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
