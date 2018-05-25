package XMLandSecurity.backend1.service;

import java.util.List;
import XMLandSecurity.backend1.domain.AdditionalService;

public interface AdditionalServiceService {

    List<AdditionalService> findAll();

    AdditionalService findOne(Long id);

    AdditionalService save(AdditionalService additionalService);

    void delete(Long id);



}
