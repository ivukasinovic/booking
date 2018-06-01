package com.bek.bek.domain;


import java.io.Serializable;



public class TypeOfLodging implements Serializable {


    private Long id;


    private String label;
  
   // po pravilu false ali zbog Admina true, trebala bi nova klasa a ova bez ovog atributa da ostane za sifrarnik !!!

   private String name;

    public TypeOfLodging() {
    }

    public TypeOfLodging(String label, String name) {
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
