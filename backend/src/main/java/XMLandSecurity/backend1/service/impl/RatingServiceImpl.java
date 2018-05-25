package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Rating;
import XMLandSecurity.backend1.repository.RatingRepository;
import XMLandSecurity.backend1.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;


    @Override
    public List<Rating> findAll() {
        return ratingRepository.findAll();
    }

    @Override
    public Rating findOne(Long id) {
        return ratingRepository.findOne(id);
    }

    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(Long id) {
        ratingRepository.delete(id);
    }
}
