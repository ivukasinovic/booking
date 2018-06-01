package com.bek.bek.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;



public class User implements Serializable {


    private Long id;


    private String username;


    private String passwordHash;


    private String name;


    private String surname;


    private String email;


    private String city;


    private String adress;



    private String number;


    private Role role;



    private Date lastPasswordReset;


    private boolean activated;


    private List<Message> receviedMessages = new ArrayList<Message>();


    private List<Message> sentMessages = new ArrayList<Message>();


    private List<Reservation> reservations = new ArrayList<Reservation>();


    private List<Lodging> lodgings = new ArrayList<Lodging>();


    private List<Rating> ratings = new ArrayList<Rating>();


    private List<Comment> comments = new ArrayList<Comment>();


    public User() {
    }

//    public User(String username, String passwordHash, String name, String surname, String email, String city, String number, Role role, Date lastPasswordReset, boolean activated, List<Message> receviedMessages, List<Message> sentMessages, List<Reservation> reservations) {
//        this.username = username;
//        this.passwordHash = passwordHash;
//        this.name = name;
//        this.surname = surname;
//        this.email = email;
//        this.city = city;
//        this.number = number;
//        this.role = role;
//        this.lastPasswordReset = lastPasswordReset;
//        this.activated = activated;
//        this.receviedMessages = receviedMessages;
//        this.sentMessages = sentMessages;
//        this.reservations = reservations;
//    }

    public List<Message> getReceviedMessages() {
        return receviedMessages;
    }

    public void setReceviedMessages(List<Message> receviedMessages) {
        this.receviedMessages = receviedMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getLastPasswordReset() {
        return lastPasswordReset;
    }

    public void setLastPasswordReset(Date lastPasswordReset) {
        this.lastPasswordReset = lastPasswordReset;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getAdress() {
        return this.adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Lodging> getLodgings() {
        return lodgings;
    }

    public void setLodgings(List<Lodging> lodgings) {
        this.lodgings = lodgings;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
