package XMLandSecurity.backend1.service.impl;

import XMLandSecurity.backend1.domain.AdditionalService;
import XMLandSecurity.backend1.repository.AdditionalServiceRepository;
import XMLandSecurity.backend1.service.AdditionalServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdditionalServiceServiceImpl implements AdditionalServiceService {

    @Autowired
    private AdditionalServiceRepository additionalServiceRepository;

    @Override
    public List<AdditionalService> findAll() {
        return additionalServiceRepository.findAll();
    }

    @Override
    public AdditionalService findOne(Long id) {
        return additionalServiceRepository.findOne(id);
    }

    @Override
    public AdditionalService save(AdditionalService additionalService) {
        return additionalServiceRepository.save(additionalService);
    }

    @Override
    public void delete(Long id) {
        additionalServiceRepository.delete(id);
    }


}
