package com.project.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="additionalService")

public class AdditionalService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement(name="id", required=true)
    private Long id;

    @OneToMany(mappedBy = "additionalService", cascade = CascadeType.ALL)
    @XmlElementWrapper(name="additionalService_list", required=true)
    @XmlElement(name="city", required=true)
    private List<AdditionalServiceAdmin> additionalService_list = new ArrayList<AdditionalServiceAdmin>();


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JoinColumn(name = "lodging_id", nullable = false)
    @ManyToOne
    @XmlElement(name="lodging", required=true)
    private Lodging lodging;


    public AdditionalService() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



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
