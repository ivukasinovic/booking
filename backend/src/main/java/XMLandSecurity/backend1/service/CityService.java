package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.City;

import java.util.List;

public interface CityService {

    List<City> findAll();

    City findOne(Long id);

    City save(City user);

    void delete(Long id);

    List<City> search(String name);
}
