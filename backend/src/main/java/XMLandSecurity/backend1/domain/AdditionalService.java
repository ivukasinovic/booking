package XMLandSecurity.backend1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "additionalService")

public class AdditionalService implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @XmlElement(name = "id", required = true)
    private Long id;

    @Column(name = "name")
    @XmlElement(name = "name", required = true)
    private String name;
    @JsonIgnore
    @JoinTable(
            name = "lodging_additional_service",
            joinColumns = {@JoinColumn(name = "additional_service_id")},
            inverseJoinColumns = {@JoinColumn(name = "lodging_id")})
    @XmlElementWrapper(name = "lodgingList", required = true)
    @XmlElement(name = "lodging", required = true)
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Lodging> lodgingList;

    public AdditionalService(Long aLong, String s, String message) {
    }


    public AdditionalService(int i) {
    }

    public AdditionalService(String name, List<Lodging> lodgingList) {
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

    public List<Lodging> getLodgingList() {
        return lodgingList;
    }

    public void setLodgingList(List<Lodging> lodgingList) {
        this.lodgingList = lodgingList;
    }
}
