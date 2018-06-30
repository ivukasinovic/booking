package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Rating;

import java.util.List;

public interface RatingService {

    List<Rating> findAll();

    Rating findOne(Long id);

    Rating save(Rating rating);

    void delete(Long id);

}
