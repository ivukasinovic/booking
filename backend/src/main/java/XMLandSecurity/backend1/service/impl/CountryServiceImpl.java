package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.Country;
import XMLandSecurity.backend1.repository.CountryRepository;
import XMLandSecurity.backend1.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    private CountryRepository countryRepository;


    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Country findOne(Long id) {
        return countryRepository.findOne(id);
    }

    @Override
    public Country save(Country user) {
        return countryRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        countryRepository.delete(id);
    }
}
