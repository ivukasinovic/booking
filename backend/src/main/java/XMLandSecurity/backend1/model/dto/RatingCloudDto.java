package XMLandSecurity.backend1.model.dto;

import java.util.ArrayList;

public class RatingCloudDto {

    private int ratingValue;
    private ArrayList<Long> lodgingId;
    public RatingCloudDto(){

    }

    public RatingCloudDto(int ratingValue, ArrayList<Long> lodgingId) {
        this.ratingValue = ratingValue;
        this.lodgingId = lodgingId;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public ArrayList<Long> getLodgingId() {
        return lodgingId;
    }

    public void setLodgingId(ArrayList<Long> lodgingId) {
        this.lodgingId = lodgingId;
    }
}
