package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.City;
import XMLandSecurity.backend1.repository.CityRepository;
import XMLandSecurity.backend1.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public City findOne(Long id) {
        return cityRepository.findOne(id);
    }

    @Override
    public City save(City user) {
        return cityRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        cityRepository.delete(id);
    }

}
