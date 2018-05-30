package com.bek.bek.domain;



import java.io.Serializable;


public class AdditionalServiceAdmin implements Serializable {


    private Long id;


    private String name;


    private AdditionalService additionalService;


    public AdditionalServiceAdmin() { }

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

    public AdditionalService getAdditionalService() {
        return additionalService;
    }

    public void setAdditionalService(AdditionalService additionalService) {
        this.additionalService = additionalService;
    }
}
