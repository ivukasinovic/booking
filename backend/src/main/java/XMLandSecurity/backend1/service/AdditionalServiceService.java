package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.AdditionalService;

import java.util.List;

public interface AdditionalServiceService {

    List<AdditionalService> findAll();

    AdditionalService findOne(Long id);

    AdditionalService save(AdditionalService additionalService);

    void delete(Long id);


}
