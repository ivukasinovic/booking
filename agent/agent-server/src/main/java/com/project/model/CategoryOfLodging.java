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
@XmlRootElement(name="categoryOfLodging")

public class CategoryOfLodging implements Serializable {

    @Id
    @Column(name = "id")
    @XmlElement(name="id", required=true)
    private Long id;

    @Column(name = "label")
    @XmlElement(name="label", required=true)
    private String label;

    @Column(name = "name")
    @XmlElement(name="name", required=true)
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
