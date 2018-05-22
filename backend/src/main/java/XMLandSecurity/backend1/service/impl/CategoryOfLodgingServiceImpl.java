package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.CategoryOfLodging;
import XMLandSecurity.backend1.repository.CategoryOfLodgingRepository;
import XMLandSecurity.backend1.service.CategoryOfLodgingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryOfLodgingServiceImpl implements CategoryOfLodgingService {

    @Autowired
    private CategoryOfLodgingRepository categoryOfLodgingRepository;

    @Override
    public List<CategoryOfLodging> findAll() {
        return categoryOfLodgingRepository.findAll();
    }

    @Override
    public CategoryOfLodging findOne(Long id) {
        return categoryOfLodgingRepository.findOne(id);
    }

    @Override
    public CategoryOfLodging save(CategoryOfLodging categoryOfLodging) {
        return categoryOfLodgingRepository.save(categoryOfLodging);
    }

    @Override
    public void delete(Long id) {
        categoryOfLodgingRepository.delete(id);
    }
}
