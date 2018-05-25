package XMLandSecurity.backend1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import java.io.Serializable;
import java.util.List;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="additionalService")
@XmlType
public class AdditionalService implements Serializable {

    @Id
    @Column(name = "id")
    @XmlElement(name="id", required=true)
    private Long id;

    @Column(name = "name")
    @XmlElement(name="name", required=true)
    private String name;

    @JsonIgnore
    @JoinTable(
            name = "lodging_additional_service",
            joinColumns = {@JoinColumn(name = "additional_service_id")},
            inverseJoinColumns = {@JoinColumn(name = "lodging_id")})
    @XmlElementWrapper(name="lodgingList", required=true)
    @XmlElement(name="lodging", required=true)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Lodging> lodgingList;


    public AdditionalService() {
    }


    public AdditionalService(Long id, String name, List<Lodging> lodgingList) {
        this.id = id;
        this.name = name;
        this.lodgingList = lodgingList;
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

    public List<Lodging> getLodgingList() {
        return lodgingList;
    }

    public void setLodgingList(List<Lodging> lodgingList) {
        this.lodgingList = lodgingList;
    }
}
