package com.project.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="typeOfLodging")

public class TypeOfLodging implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement(name="id", required=true)
    private Long id;

    @Column(name = "label", nullable = false)
    @XmlElement(name="label", required=true)
    private String label;
  
    @XmlElement(name="name", required=true)
    @Column(name = "name", nullable = true)     // po pravilu false ali zbog Admina true, trebala bi nova klasa a ova bez ovog atributa da ostane za sifrarnik !!!
    private String name;

    public TypeOfLodging() {
    }

    public TypeOfLodging(String label, String name, List<Lodging> lodgingList) {
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
