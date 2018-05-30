package com.bek.bek.domain;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class AdditionalService implements Serializable {


    private Long id;

//    @Column(name = "name")
//    @XmlElement(name="name", required=true)
//    private String name;


    private List<AdditionalServiceAdmin> additionalService_list = new ArrayList<AdditionalServiceAdmin>();



    private Lodging lodging;


//    @JsonIgnore
//    @JoinTable(
//            name = "lodging_additional_service",
//            joinColumns = {@JoinColumn(name = "additional_service_id")},
//            inverseJoinColumns = {@JoinColumn(name = "lodging_id")})
//    @XmlElementWrapper(name="lodgingList", required=true)
//    @XmlElement(name="lodging", required=true)
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Lodging> lodgingList;


    public AdditionalService() {
    }

//
//    public AdditionalService(Long id, String name, List<Lodging> lodgingList) {
//        this.id = id;
//        this.name = name;
//        this.lodgingList = lodgingList;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }

    public List<AdditionalServiceAdmin> getAdditionalService_list() {
        return additionalService_list;
    }

    public void setAdditionalService_list(List<AdditionalServiceAdmin> additionalService_list) {
        this.additionalService_list = additionalService_list;
    }

    public Lodging getLodging() {
        return lodging;
    }

    public void setLodging(Lodging lodging) {
        this.lodging = lodging;
    }
}
