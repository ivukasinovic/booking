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

    @Column(name = "parking")
    private Boolean parking;

    @Column(name = "wi_fi")
    private Boolean wifi;

    @Column(name = "breakfast")
    private Boolean breakfast;

    @Column(name = "half_board")
    private Boolean halfBoard;

    @Column(name = "full_board")
    private Boolean fullBoard;

    @Column(name = "tv")
    private Boolean tv;

    @Column(name = "kitchen")
    private Boolean kitchen;

    @Column(name = "private_bathroom")
    private Boolean privateBathroom;

    public AdditionalService() {
    }

    public AdditionalService(Long id, Boolean parking, Boolean wifi, Boolean breakfast, Boolean halfBoard, Boolean fullBoard, Boolean tv, Boolean kitchen, Boolean privateBathroom, List<Lodging> lodgingList) {
        this.id = id;
        this.parking = parking;
        this.wifi = wifi;
        this.breakfast = breakfast;
        this.halfBoard = halfBoard;
        this.fullBoard = fullBoard;
        this.tv = tv;
        this.kitchen = kitchen;
        this.privateBathroom = privateBathroom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getParking() {
        return parking;
    }

    public void setParking(Boolean parking) {
        this.parking = parking;
    }

    public Boolean getWifi() {
        return wifi;
    }

    public void setWifi(Boolean wifi) {
        this.wifi = wifi;
    }

    public Boolean getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Boolean breakfast) {
        this.breakfast = breakfast;
    }

    public Boolean getHalfBoard() {
        return halfBoard;
    }

    public void setHalfBoard(Boolean halfBoard) {
        this.halfBoard = halfBoard;
    }

    public Boolean getFullBoard() {
        return fullBoard;
    }

    public void setFullBoard(Boolean fullBoard) {
        this.fullBoard = fullBoard;
    }

    public Boolean getTv() {
        return tv;
    }

    public void setTv(Boolean tv) {
        this.tv = tv;
    }

    public Boolean getKitchen() {
        return kitchen;
    }

    public void setKitchen(Boolean kitchen) {
        this.kitchen = kitchen;
    }

    public Boolean getPrivateBathroom() {
        return privateBathroom;
    }

    public void setPrivateBathroom(Boolean privateBathroom) {
        this.privateBathroom = privateBathroom;
    }

}
