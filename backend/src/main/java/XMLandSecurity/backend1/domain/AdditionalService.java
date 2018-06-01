package XMLandSecurity.backend1.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="additionalService")
@XmlType
public class AdditionalService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement(name="id", required=true)
    private Long id;

//    @Column(name = "name")
//    @XmlElement(name="name", required=true)
//    private String name;

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
//    @JsonIgnore
//    @JoinTable(
//            name = "lodging_additional_service",
//            joinColumns = {@JoinColumn(name = "additional_service_id")},
//            inverseJoinColumns = {@JoinColumn(name = "lodging_id")})
//    @XmlElementWrapper(name="lodgingList", required=true)
//    @XmlElement(name="lodging", required=true)
//    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Lodging> lodgingList;


    public AdditionalService(int i) {
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
