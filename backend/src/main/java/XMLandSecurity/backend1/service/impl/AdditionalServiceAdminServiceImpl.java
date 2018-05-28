package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.AdditionalServiceAdmin;
import XMLandSecurity.backend1.repository.AdditionalServiceAdminRepository;
import XMLandSecurity.backend1.service.AdditionalServiceAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdditionalServiceAdminServiceImpl implements AdditionalServiceAdminService {

    @Autowired
    private AdditionalServiceAdminRepository additionalServiceAdminRepository;

    @Override
    public List<AdditionalServiceAdmin> findAll() {
        return additionalServiceAdminRepository.findAll();
    }

    @Override
    public AdditionalServiceAdmin findOne(Long id) {
        return additionalServiceAdminRepository.findOne(id);
    }

    @Override
    public AdditionalServiceAdmin save(AdditionalServiceAdmin additionalService) {
        return additionalServiceAdminRepository.save(additionalService);
    }

    @Override
    public void delete(Long id) {
        additionalServiceAdminRepository.delete(id);
    }
}
