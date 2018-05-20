package XMLandSecurity.backend1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categoriesoflodgings")
public class CategoryOfLodging implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "label")
    private String label;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lodging> lodgingList;

    public CategoryOfLodging() {
    }

    public CategoryOfLodging(Long id, String label, String name, List<Lodging> lodgingList) {
        this.id = id;
        this.label = label;
        this.name = name;
        this.lodgingList = lodgingList;
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

    public List<Lodging> getLodgingList() {
        return lodgingList;
    }

    public void setLodgingList(List<Lodging> lodgingList) {
        this.lodgingList = lodgingList;
    }
}
