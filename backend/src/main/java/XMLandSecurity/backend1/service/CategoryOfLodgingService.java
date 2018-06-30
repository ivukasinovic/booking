package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.CategoryOfLodging;

import java.util.List;

public interface CategoryOfLodgingService {

    List<CategoryOfLodging> findAll();

    CategoryOfLodging findOne(Long id);

    CategoryOfLodging save(CategoryOfLodging categoryOfLodging);

    void delete(Long id);

}
