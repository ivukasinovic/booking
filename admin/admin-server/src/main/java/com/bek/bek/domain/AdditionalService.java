package com.bek.bek.domain;


import java.io.Serializable;
import java.util.List;

public class AdditionalService implements Serializable {


    private Long id;

    private String name;

//    private List<Lodging> lodgingList;;

    public AdditionalService() {
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

//    public List<Lodging> getLodgingList() {
//        return lodgingList;
//    }
//
//    public void setLodgingList(List<Lodging> lodgingList) {
//        this.lodgingList = lodgingList;
//    }
}
