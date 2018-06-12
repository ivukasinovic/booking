
package com.bek.bek.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Country implements Serializable {


    private Long id;


    private String name;


    private String code;



    private List<City> cityList = new ArrayList<City>();

    public Country() {
    }
    public Country(String name, String code, List<City> cityList) {
        this.name = name;
        this.code = code;
        this.cityList = cityList;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<City> getCityList() {
        return cityList;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }
}