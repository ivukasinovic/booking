package com.bek.bek.domain;


import java.io.Serializable;
import java.util.List;


public class CategoryOfLodging implements Serializable {


    private Long id;


    private String label;


    private String name;

    public CategoryOfLodging() {
    }

    public CategoryOfLodging(Long id, String label, String name, List<Lodging> lodgingList) {
        this.id = id;
        this.label = label;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
