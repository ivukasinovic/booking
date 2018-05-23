package XMLandSecurity.backend1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class AdditionalService implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "lodging_additional_service",
            joinColumns = {@JoinColumn(name = "additional_service_id")},
            inverseJoinColumns = {@JoinColumn(name = "lodging_id")}
            )
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
