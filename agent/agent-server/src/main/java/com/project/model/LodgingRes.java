package com.project.model;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


@Entity
public class LodgingRes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String title;
    protected long agent;
    protected long city;
    protected String address;
    protected long type;
    protected long category;
    protected String details;
    protected String image;
    protected BigInteger personsNumber;
    @ManyToMany(mappedBy = "lodgingList", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    protected List<AdditionalService> additionService;

    public LodgingRes() {
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

    public void setTitle(String value) {
        this.title = value;
    }

    public long getAgent() {
        return agent;
    }

    public void setAgent(long value) {
        this.agent = value;
    }

    public long getCity() {
        return city;
    }

    public void setCity(long value) {
        this.city = value;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String value) {
        this.address = value;
    }

    public long getType() {
        return type;
    }

    public void setType(long value) {
        this.type = value;
    }

    public long getCategory() {
        return category;
    }

    public void setCategory(long value) {
        this.category = value;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String value) {
        this.details = value;
    }

    public String getImage() {
        return image;
    }


    public void setImage(String value) {
        this.image = value;
    }

    public BigInteger getPersonsNumber() {
        return personsNumber;
    }

    public void setPersonsNumber(BigInteger value) {
        this.personsNumber = value;
    }

    public List<AdditionalService> getAdditionService() {
        return additionService;
    }

    public void setAdditionService(List<AdditionalService> additionService) {
        this.additionService = additionService;
    }


}
