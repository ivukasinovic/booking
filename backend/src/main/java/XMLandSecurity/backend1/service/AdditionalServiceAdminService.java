package XMLandSecurity.backend1.service;

import XMLandSecurity.backend1.domain.AdditionalServiceAdmin;

import java.util.List;

public interface AdditionalServiceAdminService {

    List<AdditionalServiceAdmin> findAll();

    AdditionalServiceAdmin findOne(Long id);

    AdditionalServiceAdmin save(AdditionalServiceAdmin additionalService);

    void delete(Long id);

}
