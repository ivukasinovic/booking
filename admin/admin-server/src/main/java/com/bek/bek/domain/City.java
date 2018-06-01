package com.bek.bek.domain;


import java.io.Serializable;
import java.util.List;


public class City implements Serializable {


    private Long id;


    private String name;


    private Country country;

    public City() {
    }

    public City(String name, Country country, List<Lodging> lodgingList) {
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

}
