package com.project.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Image implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url", nullable = false)
    private String url;

    @ManyToOne
    private LodgingRes lodgingRes;

    public Image() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LodgingRes getLodging() {
        return lodgingRes;
    }

    public void setLodging(LodgingRes lodging) {
        this.lodgingRes = lodging;
    }
}
