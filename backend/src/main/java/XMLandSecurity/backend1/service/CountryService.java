package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findOne(Long id);

    Country save(Country user);

    void delete(Long id);

}
