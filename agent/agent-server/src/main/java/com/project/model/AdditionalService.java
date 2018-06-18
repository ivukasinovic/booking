package com.project.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class AdditionalService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;


    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<LodgingRes> lodgingList;


    public AdditionalService(int i) {
    }

    public AdditionalService(String name, List<LodgingRes> lodgingList) {
        this.name = name;
        this.lodgingList = lodgingList;
    }

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

    public List<LodgingRes> getLodgingList() {
        return lodgingList;
    }

    public void setLodgingList(List<LodgingRes> lodgingList) {
        this.lodgingList = lodgingList;
    }
}
